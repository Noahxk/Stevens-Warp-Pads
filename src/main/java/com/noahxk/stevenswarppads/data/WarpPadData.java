package com.noahxk.stevenswarppads.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class WarpPadData {
    private final BlockPos pos;
    private final String name;

    public WarpPadData(BlockPos pos, String name) {
        this.pos = pos;
        this.name = name;
    }

    public CompoundTag serialize() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("pos", pos.asLong());
        tag.putString("name", name);

        return tag;
    }

    public static WarpPadData deserialize(CompoundTag tag) {
        return new WarpPadData(BlockPos.of(tag.getLong("pos")), tag.getString("name"));
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        WarpPadData o = (WarpPadData) obj;
        if(o.getPos().getX() == this.pos.getX() && o.getPos().getY() == this.pos.getY() && o.getPos().getZ() == this.pos.getZ()) {
            return true;
        } return false;
    }
}
