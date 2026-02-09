package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public enum NoteBlockSound {
    // Sons de Note Block do Minecraft
    HARP(SoundEvents.NOTE_BLOCK_HARP, "Harp"),
    BASS(SoundEvents.NOTE_BLOCK_BASS, "Bass"),
    BASEDRUM(SoundEvents.NOTE_BLOCK_BASEDRUM, "Bass Drum"),
    SNARE(SoundEvents.NOTE_BLOCK_SNARE, "Snare"),
    HAT(SoundEvents.NOTE_BLOCK_HAT, "Hat"),
    GUITAR(SoundEvents.NOTE_BLOCK_GUITAR, "Guitar"),
    FLUTE(SoundEvents.NOTE_BLOCK_FLUTE, "Flute"),
    BELL(SoundEvents.NOTE_BLOCK_BELL, "Bell"),
    CHIME(SoundEvents.NOTE_BLOCK_CHIME, "Chime"),
    XYLOPHONE(SoundEvents.NOTE_BLOCK_XYLOPHONE, "Xylophone"),
    IRON_XYLOPHONE(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, "Iron Xylophone"),
    COW_BELL(SoundEvents.NOTE_BLOCK_COW_BELL, "Cow Bell"),
    DIDGERIDOO(SoundEvents.NOTE_BLOCK_DIDGERIDOO, "Didgeridoo"),
    BIT(SoundEvents.NOTE_BLOCK_BIT, "Bit"),
    BANJO(SoundEvents.NOTE_BLOCK_BANJO, "Banjo"),
    PLING(SoundEvents.NOTE_BLOCK_PLING, "Pling");

    private final Holder.Reference<SoundEvent> sound;
    private final String displayName;

    NoteBlockSound(Holder.Reference<SoundEvent> sound, String displayName) {
        this.sound = sound;
        this.displayName = displayName;
    }

    public SoundEvent getSound() {
        return sound.value();
    }

    public String getDisplayName() {
        return displayName;
    }

    // Converte pitch (0-24) para valor de pitch do Minecraft (0.5 - 2.0)
    public static float getPitchFromNote(int note) {
        // Note Block: 0 = F#3, 24 = F#5
        // Minecraft pitch: 0.5 = uma oitava abaixo, 2.0 = uma oitava acima
        return (float) Math.pow(2.0, (note - 12) / 12.0);
    }
}