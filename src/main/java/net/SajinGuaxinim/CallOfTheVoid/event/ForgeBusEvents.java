package net.SajinGuaxinim.CallOfTheVoid.event;

import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicPlayer;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class ForgeBusEvents {

    public static void onServerTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            MusicPlayer.tick(serverLevel);
        }
    }
}