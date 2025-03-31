package com.noahxk.stevenswarppads.screen.custom;

import com.noahxk.stevenswarppads.block.ModBlocks;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class WarpPadCoreMenu extends AbstractContainerMenu {
    public final WarpPadCoreBlockEntity blockEntity;
    private final Level level;

    public WarpPadCoreMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv ,inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public WarpPadCoreMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.WARP_PAD_CORE_MENU.get(), containerId);
        this.blockEntity = ((WarpPadCoreBlockEntity) blockEntity);
        this.level = inv.player.level();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.WARP_PAD_CORE.get());
    }
}
