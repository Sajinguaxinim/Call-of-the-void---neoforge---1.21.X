package net.SajinGuaxinim.CallOfTheVoid.bard.music;

public class MusicNote {
    private final int tick;           // Quando tocar (em ticks)
    private final int note;           // Pitch (0-24)
    private final NoteBlockSound instrument;
    private final float volume;

    public MusicNote(int tick, int note, NoteBlockSound instrument, float volume) {
        this.tick = tick;
        this.note = note;
        this.instrument = instrument;
        this.volume = volume;
    }

    public int getTick() { return tick; }
    public int getNote() { return note; }
    public NoteBlockSound getInstrument() { return instrument; }
    public float getVolume() { return volume; }
    public float getPitch() { return NoteBlockSound.getPitchFromNote(note); }
}