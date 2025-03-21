package com.noahxk.stevenswarppads.block.custom;

import com.mojang.serialization.MapCodec;
import com.noahxk.stevenswarppads.block.entity.WarpPadBlockEntity;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
}
