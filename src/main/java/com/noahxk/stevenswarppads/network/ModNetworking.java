package com.noahxk.stevenswarppads.network;

import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpPadNameChangePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetworking {

    @SubscribeEvent
    public void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                ServerboundWarpPadNameChangePacket.TYPE,
                ServerboundWarpPadNameChangePacket.STREAM_CODEC,
                ServerPayloadHandler::handleDataOnMain
        );
    }
}
