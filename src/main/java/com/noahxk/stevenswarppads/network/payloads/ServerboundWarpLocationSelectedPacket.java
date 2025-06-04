package com.noahxk.stevenswarppads.network.payloads;

import com.noahxk.stevenswarppads.StevensWarpPads;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ServerboundWarpLocationSelectedPacket(String fromPadId, String toPadId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ServerboundWarpLocationSelectedPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "warplocationselected"));

    public static final StreamCodec<ByteBuf, ServerboundWarpLocationSelectedPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::fromPadId,
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::toPadId,
            ServerboundWarpLocationSelectedPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
