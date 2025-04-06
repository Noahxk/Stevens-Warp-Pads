package com.noahxk.stevenswarppads.block.custom;

import com.mojang.serialization.MapCodec;
import com.noahxk.stevenswarppads.block.entity.WarpPadBlockEntity;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WarpPadBlock extends BaseEntityBlock {
    public static final MapCodec<WarpPadBlock> CODEC = simpleCodec(WarpPadBlock::new);

    public WarpPadBlock(Properties properties) {
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
        return new WarpPadBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        int xCoord = pos.getX();
        int yCoord = pos.getY();
        int zCoord = pos.getZ();

        int i = 0;
        for(int x = xCoord - 2; x < xCoord + 3; x++)
            for(int z = zCoord - 2; z < zCoord + 3; z++) {
                BlockEntity block = level.getBlockEntity(new BlockPos(x, yCoord, z));
                if(block != null && block instanceof WarpPadCoreBlockEntity) {
                    ((WarpPadCoreBlockEntity) block).formationCheck();
                }
            }

        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        WarpPadBlockEntity block = (WarpPadBlockEntity) level.getBlockEntity(pos);
        if(block != null && block.hasParent()) {
            WarpPadCoreBlockEntity core = (WarpPadCoreBlockEntity) level.getBlockEntity(new BlockPos(block.getParentX(), block.getParentY(), block.getParentZ()));
            if(core != null) core.resetWarpPad();
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(player.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY) return InteractionResult.FAIL;
        WarpPadBlockEntity blockEntity = (WarpPadBlockEntity) level.getBlockEntity(pos);

        if(blockEntity.hasParent()) {
            BlockPos parentBlockEntityPos = new BlockPos(blockEntity.getParentX(), blockEntity.getParentY(), blockEntity.getParentZ());
            WarpPadCoreBlockEntity parentBlockEntity = (WarpPadCoreBlockEntity) level.getBlockEntity(parentBlockEntityPos);

            player.openMenu(new SimpleMenuProvider(parentBlockEntity, Component.translatable("menu.title.stevenswarppads.warp_pad_core")), parentBlockEntityPos);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        } else return InteractionResult.FAIL;
    }
}
