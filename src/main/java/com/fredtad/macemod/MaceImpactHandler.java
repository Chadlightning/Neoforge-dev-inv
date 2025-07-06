package com.fredtad.macemod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.minecraft.world.item.MaceItem;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.server.level.ServerPlayer;


@EventBusSubscriber(modid = "maceimpactframes")   // default bus = GAME
public final class MaceImpactHandler {

    @SubscribeEvent
    public static void onLivingDamage(net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Post event) {
        LivingEntity victim = event.getEntity();

        Entity raw = event.getSource().getEntity();
        if (!(raw instanceof Player player)) return;

        // Only proceed if the AttackEntityEvent marked this player
        var data = player.getPersistentData();
        if (!data.getBoolean("macemod.pendingSlam")) return;

        float damage = event.getNewDamage();         // final damage
        System.out.println("Mace slam dealt " + damage);


    }
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player attacker = event.getEntity();
        if (attacker.level().isClientSide()) return;
        System.out.println("AttackEntityEvent fired");

        if (!attacker.getMainHandItem().is(Items.MACE)) return;
        if (!MaceItem.canSmashAttack(attacker)) return;

        if (event.getTarget() instanceof net.minecraft.world.entity.LivingEntity victim) {
            // freeze / slowdown will go here
            attacker.getPersistentData().putBoolean("macemod.pendingSlam", true);

            System.out.println("Mace hit landed!");
        }
    }
}
