package com.noahxk.stevenswarppads.block.custom;

import com.mojang.serialization.MapCodec;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
        WarpPadCoreBlockEntity block = (WarpPadCoreBlockEntity) level.getBlockEntity(pos);
        System.out.println(block.checkWarpPadFormed());

        super.onPlace(state, level, pos, oldState, movedByPiston);
    }
}
