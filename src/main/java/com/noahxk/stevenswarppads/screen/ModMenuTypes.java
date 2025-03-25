package com.noahxk.stevenswarppads.screen;

import com.noahxk.stevenswarppads.StevensWarpPads;
import com.noahxk.stevenswarppads.screen.custom.WarpPadCoreMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, StevensWarpPads.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<WarpPadCoreMenu>> WARP_PAD_CORE_MENU = registerMenuType("warp_pad_core_menu", WarpPadCoreMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
