package com.noahxk.stevenswarppads.network.payloads;

import com.noahxk.stevenswarppads.StevensWarpPads;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ServerboundWarpLocationSelectedPacket(long fromPos, String fromDimension, String fromId, long toPos, String toDimension, String toId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ServerboundWarpLocationSelectedPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "warplocationselected"));

    public static final StreamCodec<ByteBuf, ServerboundWarpLocationSelectedPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG,
            ServerboundWarpLocationSelectedPacket::fromPos,
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::fromDimension,
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::fromId,
            ByteBufCodecs.VAR_LONG,
            ServerboundWarpLocationSelectedPacket::toPos,
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::toDimension,
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpLocationSelectedPacket::toId,
            ServerboundWarpLocationSelectedPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
