package com.noahxk.stevenswarppads.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WarpPadBlockEntity extends BlockEntity {
    public WarpPadBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_PAD_BLOCK_ENTITY.get(), pos, blockState);
    }

    private boolean hasParent;
    private int parentX, parentY, parentZ;

    public boolean hasParent() {
        return this.hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public int getParentX() {
        return this.parentX;
    }

    public int getParentY() {
        return this.parentY;
    }

    public int getParentZ() {
        return this.parentZ;
    }

    public void setParentPos(int x, int y, int z) {
        this.parentX = x;
        this.parentY = y;
        this.parentZ = z;
    }
}
