package com.noahxk.stevenswarppads.block.entity;

import com.noahxk.stevenswarppads.StevensWarpPads;
import com.noahxk.stevenswarppads.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, StevensWarpPads.MODID);

    public static final Supplier<BlockEntityType<WarpPadCoreBlockEntity>> WARP_PAD_CORE_BLOCK_ENTITY = BLOCK_ENTITIES.register("warp_pad_core_block_entity", () -> BlockEntityType.Builder.of(
            WarpPadCoreBlockEntity::new, ModBlocks.WARP_PAD_CORE.get()).build(null));

    public static final Supplier<BlockEntityType<WarpPadBlockEntity>> WARP_PAD_BLOCK_ENTITY = BLOCK_ENTITIES.register("warp_pad_block_entity", () -> BlockEntityType.Builder.of(
            WarpPadBlockEntity::new, ModBlocks.WARP_PAD_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
