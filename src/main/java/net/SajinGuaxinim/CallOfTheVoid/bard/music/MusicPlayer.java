package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MusicPlayer {
    private static final Map<UUID, PlayingMusic> PLAYING = new HashMap<>();

    public static void startPlaying(Player player, BardSong song) {
        stopPlaying(player);
        PLAYING.put(player.getUUID(), new PlayingMusic(song, 0));
    }

    public static void stopPlaying(Player player) {
        PLAYING.remove(player.getUUID());
    }

    public static boolean isPlaying(Player player) {
        return PLAYING.containsKey(player.getUUID());
    }

    public static void tick(ServerLevel level) {
        PLAYING.entrySet().removeIf(entry -> {
            Player player = level.getPlayerByUUID(entry.getKey());
            if (player == null) return true;

            PlayingMusic playing = entry.getValue();
            playing.tick++;

            // Toca as notas deste tick
            for (MusicNote note : playing.song.getNotesAtTick(playing.tick)) {
                playNote(level, player.position(), note);
            }

            // Remove se terminou
            return playing.tick >= playing.song.getLength();
        });
    }

    private static void playNote(ServerLevel level, Vec3 position, MusicNote note) {
        level.playSound(
                null,
                BlockPos.containing(position),
                note.getInstrument().getSound(),
                SoundSource.RECORDS,
                note.getVolume(),
                note.getPitch()
        );
    }

    private static class PlayingMusic {
        final BardSong song;
        int tick;

        PlayingMusic(BardSong song, int tick) {
            this.song = song;
            this.tick = tick;
        }
    }
}