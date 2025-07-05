package com.fredtad.macemod;

public class HitStopHandler {
    private static boolean isFrozen = false;

    public static void startFreeze(int duration) {
        isFrozen = true;
        System.out.println("Client hitstop started for " + duration + " ticks");
    }

    public static void clearFreeze() {
        isFrozen = false;
        System.out.println("Hitstop cleared — server unfreezing world.");
    }
}