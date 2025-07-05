package com.fredtad.macemod;

public class HitStopHandler {
    private static boolean isFrozen = false;

    public static void clearFreeze() {
        isFrozen = false;
        System.out.println("Hitstop cleared — server unfreezing world.");
    }
}