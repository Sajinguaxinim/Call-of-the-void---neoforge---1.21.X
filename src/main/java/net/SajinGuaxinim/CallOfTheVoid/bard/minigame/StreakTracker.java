package net.SajinGuaxinim.CallOfTheVoid.bard.minigame;

import net.minecraft.world.entity.player.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StreakTracker {
    private static final Map<UUID, PlayerStreak> STREAKS = new HashMap<>();

    public static void addSuccess(Player player) {
        UUID uuid = player.getUUID();
        PlayerStreak streak = STREAKS.computeIfAbsent(uuid, k -> new PlayerStreak());
        streak.addSuccess();
    }

    public static void resetStreak(Player player) {
        UUID uuid = player.getUUID();
        PlayerStreak streak = STREAKS.get(uuid);
        if (streak != null) {
            streak.reset();
        }
    }

    public static int getStreak(Player player) {
        PlayerStreak streak = STREAKS.get(player.getUUID());
        return streak != null ? streak.getCurrentStreak() : 0;
    }

    public static float getStreakMultiplier(Player player) {
        int streak = getStreak(player);

        // 3+ acertos = 2x
        // 5+ acertos = 2.5x
        // 10+ acertos = 3x
        if (streak >= 10) return 3.0f;
        if (streak >= 5) return 2.5f;
        if (streak >= 3) return 2.0f;
        return 1.0f;
    }

    public static boolean hasStreakBonus(Player player) {
        return getStreak(player) >= 3;
    }

    // Classe interna para rastrear streak individual
    private static class PlayerStreak {
        private int currentStreak;
        private int bestStreak;

        public PlayerStreak() {
            this.currentStreak = 0;
            this.bestStreak = 0;
        }

        public void addSuccess() {
            currentStreak++;
            if (currentStreak > bestStreak) {
                bestStreak = currentStreak;
            }
        }

        public void reset() {
            currentStreak = 0;
        }

        public int getCurrentStreak() {
            return currentStreak;
        }

        public int getBestStreak() {
            return bestStreak;
        }
    }
}