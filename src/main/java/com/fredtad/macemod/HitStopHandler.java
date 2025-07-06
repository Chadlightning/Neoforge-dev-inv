package com.fredtad.macemod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = MaceMod.MOD_ID, value = Dist.CLIENT)
public class HitStopHandler {
    private static boolean isFrozen = false;
    private static int remainingTicks;

    public static void startFreeze(int duration) {
        isFrozen = true;
        remainingTicks = duration;
        System.out.println("Client hitstop started for " + duration + " ticks");
    }

    public static void clearFreeze() {
        isFrozen = false;
        remainingTicks = 0;
        System.out.println("Hitstop cleared — server unfreezing world.");
    }
}

