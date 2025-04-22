package com.noahxk.stevenswarppads.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.UUID;

public class WarpPadData {
    private final BlockPos pos;
    private final String name;
    private final ServerLevel dimension;
    private final UUID id;

    public WarpPadData(BlockPos pos, String name, ServerLevel dimension, UUID id) {
        this.pos = pos;
        this.name = name;
        this.dimension = dimension;
        this.id = id;
    }

    public CompoundTag serialize() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("pos", pos.asLong());
        tag.putString("name", name);
        tag.putString("dimension", dimension.dimension().location().toString());
        tag.putUUID("id", id);

        return tag;
    }

    public static WarpPadData deserialize(CompoundTag tag) {
        ResourceKey<Level> dimensionResourceKey = ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "dimension")), ResourceLocation.parse(tag.getString("dimension")));

        return new WarpPadData(BlockPos.of(tag.getLong("pos")), tag.getString("name"), ServerLifecycleHooks.getCurrentServer().getLevel(dimensionResourceKey), tag.getUUID("id"));
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public String getName() {
        return this.name;
    }

    public ServerLevel getDimension() {
        return this.dimension;
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        WarpPadData o = (WarpPadData) obj;
        return o.getId().equals(this.getId());
    }
}
