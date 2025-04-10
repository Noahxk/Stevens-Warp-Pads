package com.noahxk.stevenswarppads.block.entity;

import com.noahxk.stevenswarppads.data.WarpPadListSavedData;
import com.noahxk.stevenswarppads.screen.custom.WarpPadCoreMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class WarpPadCoreBlockEntity extends BlockEntity implements MenuProvider {
    public WarpPadCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_PAD_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    private boolean isParent, isWarping, isFormed;
    private String warpPadName = "Unnamed Warp Pad";

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putBoolean("isParent", this.isParent);
        tag.putString("warpPadName", this.warpPadName);
        tag.putBoolean("isWarping", this.isWarping);
        tag.putBoolean("isFormed", this.isFormed);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.isParent = tag.getBoolean("isParent");
        this.warpPadName = tag.getString("warpPadName");
        this.isWarping = tag.getBoolean("isWarping");
        this.isFormed = tag.getBoolean("isFormed");
    }

    public boolean isFormed() {
        return this.isFormed;
    }

    public void setIsFormed(boolean value) {
        this.isFormed = value;
    }

    public String getWarpPadName() {
        return this.warpPadName;
    }

    public void setWarpPadName(String warpPadName) {
        this.warpPadName = warpPadName;
    }

    public boolean isParent() {
        return this.isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isWarpPadShapeCorrect() {
        int xCoord = this.getBlockPos().getX();
        int yCoord = this.getBlockPos().getY();
        int zCoord = this.getBlockPos().getZ();

        int i = 0;
        for (int x = xCoord - 2; x < xCoord + 3; x++)
            for (int z = zCoord - 2; z < zCoord + 3; z++) {
                BlockEntity block = level.getBlockEntity(new BlockPos(x, yCoord, z));
                if (block instanceof WarpPadBlockEntity) {
                    if (!((WarpPadBlockEntity) block).hasParent()) {
                        i++;
                    }
                }
            }

        if (level.getBlockEntity(new BlockPos(xCoord - 2, yCoord, zCoord - 2)) instanceof WarpPadBlockEntity) return false;
        if (level.getBlockEntity(new BlockPos(xCoord + 2, yCoord, zCoord + 2)) instanceof WarpPadBlockEntity) return false;
        if (level.getBlockEntity(new BlockPos(xCoord + 2, yCoord, zCoord - 2)) instanceof WarpPadBlockEntity) return false;
        if (level.getBlockEntity(new BlockPos(xCoord - 2, yCoord, zCoord + 2)) instanceof WarpPadBlockEntity) return false;

        return (i == 20) ? true : false;
    }

    public void formWarpPad() {
        int xCoord = this.getBlockPos().getX();
        int yCoord = this.getBlockPos().getY();
        int zCoord = this.getBlockPos().getZ();

        this.setIsParent(true);

        int i = 0;
        for(int x = xCoord - 2; x < xCoord + 3; x++)
            for(int z = zCoord - 2; z < zCoord + 3; z++) {
                BlockEntity block = level.getBlockEntity(new BlockPos(x, yCoord, z));
                if(block instanceof WarpPadBlockEntity) {
                    ((WarpPadBlockEntity) block).setHasParent(true);
                    ((WarpPadBlockEntity) block).setParentPos(xCoord, yCoord, zCoord);
                }
            }

        System.out.println("Warp Pad Formed at " + this.getBlockPos().toShortString());
        this.setIsFormed(true);

        WarpPadListSavedData.addWarpPad(this.getBlockPos(), this.getWarpPadName(), this.getLevel().getServer().overworld());
    }

    public void resetWarpPad() {
        if(!this.isParent()) return;
        int xCoord = this.getBlockPos().getX();
        int yCoord = this.getBlockPos().getY();
        int zCoord = this.getBlockPos().getZ();

        this.setIsParent(false);

        int i = 0;
        for(int x = xCoord - 2; x < xCoord + 3; x++)
            for(int z = zCoord - 2; z < zCoord + 3; z++) {
                BlockEntity block = level.getBlockEntity(new BlockPos(x, yCoord, z));
                if(block instanceof WarpPadBlockEntity) {
                    ((WarpPadBlockEntity) block).reset();
                }
            }

        System.out.println("Warp Pad Reset at " + this.getBlockPos().toShortString());
        this.setIsFormed(false);

        WarpPadListSavedData.removeWarpPad(this.getBlockPos(), this.getWarpPadName(), this.getLevel().getServer().overworld());
    }

    public void formationCheck() {
        if(!level.isClientSide()) {
            if(this.isWarpPadShapeCorrect()) {
                this.formWarpPad();
            } else {
                this.resetWarpPad();
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(this.getWarpPadName());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new WarpPadCoreMenu(i, inventory, this);
    }
}
