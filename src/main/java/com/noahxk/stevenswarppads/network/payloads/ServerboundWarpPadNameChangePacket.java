package com.noahxk.stevenswarppads.network.payloads;

import com.noahxk.stevenswarppads.StevensWarpPads;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ServerboundWarpPadNameChangePacket(String newName, Long pos) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ServerboundWarpPadNameChangePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "warppadnamechange"));

    public static final StreamCodec<ByteBuf, ServerboundWarpPadNameChangePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ServerboundWarpPadNameChangePacket::newName,
            ByteBufCodecs.VAR_LONG,
            ServerboundWarpPadNameChangePacket::pos,
            ServerboundWarpPadNameChangePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}