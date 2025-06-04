package com.noahxk.stevenswarppads.network;

import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpLocationSelectedPacket;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpPadNameChangePacket;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public class ServerPayloadHandler {
    // For warp pads changing their names
    public static void handleDataOnMain(final ServerboundWarpPadNameChangePacket data, final IPayloadContext context) {
        WarpPadCoreBlockEntity blockEntity = (WarpPadCoreBlockEntity) context.player().level().getBlockEntity(BlockPos.of(data.pos()));
        if(blockEntity != null) blockEntity.setWarpPadName(data.newName());
    }

    // For starting a warp
    public static void handleDataOnMain(final ServerboundWarpLocationSelectedPacket data, final IPayloadContext context) {
        WarpStream warpStream = new WarpStream(UUID.fromString(data.fromPadId()), UUID.fromString(data.toPadId()));
        warpStream.startWarpSequence();
    }
}
