package com.noahxk.stevenswarppads.block.custom;

import com.mojang.serialization.Decoder;
import com.mojang.serialization.MapCodec;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.screen.custom.WarpPadCoreMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WarpPadCoreBlock extends BaseEntityBlock {
    public static final MapCodec<WarpPadCoreBlock> CODEC = simpleCodec(WarpPadCoreBlock::new);

    public WarpPadCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WarpPadCoreBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            WarpPadCoreBlockEntity block = (WarpPadCoreBlockEntity) level.getBlockEntity(pos);
            block.randomiseWarpPadId();
            if (block != null) block.formationCheck();
        }

        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            WarpPadCoreBlockEntity block = (WarpPadCoreBlockEntity) level.getBlockEntity(pos);
            if (block != null) block.resetWarpPad();
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(player.isCrouching() && player.getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY) {
            WarpPadCoreBlockEntity be = (WarpPadCoreBlockEntity) level.getBlockEntity(pos);
            System.out.println(be.getWarpPadId());
            System.out.println(be.getWarpPadName());
            System.out.println(be.isFormed());
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }

        if(player.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY) return InteractionResult.FAIL;
        WarpPadCoreBlockEntity blockEntity = (WarpPadCoreBlockEntity) level.getBlockEntity(pos);

        if(blockEntity.isFormed()) {
            player.openMenu(new SimpleMenuProvider(blockEntity, Component.translatable("menu.title.stevenswarppads.warp_pad_core")), pos);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        } else return InteractionResult.FAIL;
    }
}
