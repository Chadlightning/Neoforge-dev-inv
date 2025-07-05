package com.fredtad.macemod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = MaceMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ChannelHandler {
    private static final String VERSION = "1";

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VERSION);
        registrar.playToClient(StartHitStopPacket.TYPE, StartHitStopPacket.STREAM_CODEC, StartHitStopPacket::handle);
    }
}
