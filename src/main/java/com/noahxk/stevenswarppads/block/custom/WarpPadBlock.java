package com.noahxk.stevenswarppads.block.custom;

import com.mojang.serialization.MapCodec;
import com.noahxk.stevenswarppads.block.entity.WarpPadBlockEntity;
import net.minecraft.core.BlockPos;
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
}
