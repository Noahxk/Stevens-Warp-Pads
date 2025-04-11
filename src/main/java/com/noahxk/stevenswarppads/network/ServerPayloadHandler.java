package com.noahxk.stevenswarppads.network;

import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpPadNameChangePacket;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {
    public static void handleDataOnMain(final ServerboundWarpPadNameChangePacket data, final IPayloadContext context) {
        WarpPadCoreBlockEntity blockEntity = (WarpPadCoreBlockEntity) context.player().level().getBlockEntity(BlockPos.of(data.pos()));
        blockEntity.setWarpPadName(data.newName());
    }
}
