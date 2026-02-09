package net.SajinGuaxinim.CallOfTheVoid.bard.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class ParticleHandler {

    public static void spawnMusicNotes(ServerLevel level, Vec3 center, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = Math.random() * Math.PI * 2;
            double distance = Math.random() * radius;

            double x = center.x + Math.cos(angle) * distance;
            double y = center.y + Math.random() * 2;
            double z = center.z + Math.sin(angle) * distance;

            // Partícula de nota musical (colorida automaticamente)
            level.sendParticles(
                    ParticleTypes.NOTE,
                    x, y, z,
                    1, // count
                    0, 0.5, 0, // offset
                    0 // speed
            );
        }
    }

    public static void spawnHealingParticles(ServerLevel level, Vec3 center, double radius) {
        for (int i = 0; i < 20; i++) {
            double angle = Math.random() * Math.PI * 2;
            double distance = Math.random() * radius;

            double x = center.x + Math.cos(angle) * distance;
            double y = center.y + Math.random() * 2;
            double z = center.z + Math.sin(angle) * distance;

            // Partículas verdes (happy villager)
            level.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    x, y, z,
                    2,
                    0.2, 0.2, 0.2,
                    0.01
            );
        }
    }

    public static void spawnDamageParticles(ServerLevel level, Vec3 center, double radius) {
        for (int i = 0; i < 20; i++) {
            double angle = Math.random() * Math.PI * 2;
            double distance = Math.random() * radius;

            double x = center.x + Math.cos(angle) * distance;
            double y = center.y + Math.random() * 2;
            double z = center.z + Math.sin(angle) * distance;

            // Partículas vermelhas (angry villager)
            level.sendParticles(
                    ParticleTypes.ANGRY_VILLAGER,
                    x, y, z,
                    2,
                    0.2, 0.2, 0.2,
                    0.01
            );
        }
    }

    public static void spawnBuffAura(ServerLevel level, Vec3 center, double radius, int color) {
        // Círculo de partículas ao redor
        for (int i = 0; i < 32; i++) {
            double angle = (Math.PI * 2 * i) / 32;

            double x = center.x + Math.cos(angle) * radius;
            double y = center.y + 0.1;
            double z = center.z + Math.sin(angle) * radius;

            // Partículas de portal (azuis) ou end rod (brancas)
            level.sendParticles(
                    color == 0 ? ParticleTypes.PORTAL : ParticleTypes.END_ROD,
                    x, y, z,
                    1,
                    0, 0, 0,
                    0.01
            );
        }
    }
}