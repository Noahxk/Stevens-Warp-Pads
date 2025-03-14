package com.noahxk.stevenswarppads.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WarpPadCoreBlockEntity extends BlockEntity {
    public WarpPadCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_PAD_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    private boolean isParent;

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putBoolean("isParent", isParent);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.isParent = tag.getBoolean("isParent");
    }

    public boolean isParent() {
        return this.isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public int checkWarpPadFormed() {
        int xCoord = this.getBlockPos().getX();
        int yCoord = this.getBlockPos().getY();
        int zCoord = this.getBlockPos().getZ();

        int i = 0;
        for(int x = xCoord - 2; x < xCoord + 3; x++)
            for(int z = zCoord - 2; z < zCoord + 3; z++) {
                BlockEntity block = level.getBlockEntity(new BlockPos(x, yCoord, z));
                if(block instanceof WarpPadBlockEntity) {
                    i++;
                }
            }

        return i;
    }
}
