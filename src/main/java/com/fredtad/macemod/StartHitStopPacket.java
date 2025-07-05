package com.fredtad.macemod;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StartHitStopPacket(int duration) implements CustomPacketPayload {
    public static final Type<StartHitStopPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MaceMod.MOD_ID, "start_hit_stop"));
    public static final StreamCodec<RegistryFriendlyByteBuf, StartHitStopPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, StartHitStopPacket::duration, StartHitStopPacket::new);

    @Override
    public Type<StartHitStopPacket> type() {
        return TYPE;
    }

    public static void handle(StartHitStopPacket payload, net.neoforged.neoforge.network.handling.IPayloadContext ctx) {
        ctx.enqueueWork(() -> HitStopHandler.startFreeze(payload.duration()));
    }
}
