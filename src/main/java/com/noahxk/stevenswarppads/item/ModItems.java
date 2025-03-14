package com.noahxk.stevenswarppads.item;

import com.noahxk.stevenswarppads.StevensWarpPads;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StevensWarpPads.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
