package com.noahxk.stevenswarppads.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WarpPadBlockEntity extends BlockEntity {
    public WarpPadBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_PAD_BLOCK_ENTITY.get(), pos, blockState);
    }

    private boolean hasParent;
    private int parentX, parentY, parentZ;

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putBoolean("hasParent", this.hasParent);
        tag.putInt("parentX", this.parentX);
        tag.putInt("parentY", this.parentY);
        tag.putInt("parentZ", this.parentZ);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.hasParent = tag.getBoolean("hasParent");
        this.parentX = tag.getInt("parentX");
        this.parentY = tag.getInt("parentY");
        this.parentZ = tag.getInt("parentZ");
    }

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

    public void reset() {
        this.setHasParent(false);
        this.setParentPos(0,0,0);
    }
}
