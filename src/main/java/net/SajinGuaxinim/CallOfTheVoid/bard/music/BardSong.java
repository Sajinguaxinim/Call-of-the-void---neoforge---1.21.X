package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import java.util.ArrayList;
import java.util.List;

public class BardSong {
    private final String name;
    private final String author;
    private final int tempo;  // BPM ou ticks por segundo
    private final List<MusicNote> notes;
    private final int length; // Duração total em ticks

    public BardSong(String name, String author, int tempo, List<MusicNote> notes) {
        this.name = name;
        this.author = author;
        this.tempo = tempo;
        this.notes = new ArrayList<>(notes);
        this.length = calculateLength();
    }

    private int calculateLength() {
        return notes.stream()
                .mapToInt(MusicNote::getTick)
                .max()
                .orElse(0);
    }

    public String getName() { return name; }
    public String getAuthor() { return author; }
    public int getTempo() { return tempo; }
    public List<MusicNote> getNotes() { return new ArrayList<>(notes); }
    public int getLength() { return length; }

    // Retorna notas que devem tocar neste tick
    public List<MusicNote> getNotesAtTick(int tick) {
        List<MusicNote> result = new ArrayList<>();
        for (MusicNote note : notes) {
            if (note.getTick() == tick) {
                result.add(note);
            }
        }
        return result;
    }
}