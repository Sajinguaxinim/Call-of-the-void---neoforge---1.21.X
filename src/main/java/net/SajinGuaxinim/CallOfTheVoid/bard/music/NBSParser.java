package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NBSParser {

    public static BardSong parseNBS(File file) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            // Header do NBS (formato 3+)
            short length = readShort(dis);
            short height = readShort(dis);

            String name = readString(dis);
            String author = readString(dis);
            String originalAuthor = readString(dis);
            String description = readString(dis);

            short tempo = readShort(dis);
            byte autoSave = dis.readByte();
            byte autoSaveDuration = dis.readByte();
            byte timeSignature = dis.readByte();

            int minutesSpent = readInt(dis);
            int leftClicks = readInt(dis);
            int rightClicks = readInt(dis);
            int noteBlocksAdded = readInt(dis);
            int noteBlocksRemoved = readInt(dis);

            String midiName = readString(dis);

            // Pula alguns bytes
            dis.skipBytes(1); // loop
            dis.skipBytes(1); // maxLoopCount
            dis.skipBytes(2); // loopStartTick

            List<MusicNote> notes = new ArrayList<>();

            // Lê as notas
            short tick = -1;
            while (true) {
                short jumpTicks = readShort(dis);
                if (jumpTicks == 0) break;

                tick += jumpTicks;

                short layer = -1;
                while (true) {
                    short jumpLayers = readShort(dis);
                    if (jumpLayers == 0) break;

                    layer += jumpLayers;

                    byte instrument = dis.readByte();
                    byte key = dis.readByte();

                    // Converte instrumento NBS para NoteBlockSound
                    NoteBlockSound sound = convertNBSInstrument(instrument);

                    notes.add(new MusicNote(tick, key, sound, 1.0f));
                }
            }

            return new BardSong(name, author, tempo, notes);
        }
    }

    private static NoteBlockSound convertNBSInstrument(byte nbsInstrument) {
        return switch (nbsInstrument) {
            case 0 -> NoteBlockSound.HARP;
            case 1 -> NoteBlockSound.BASS;
            case 2 -> NoteBlockSound.BASEDRUM;
            case 3 -> NoteBlockSound.SNARE;
            case 4 -> NoteBlockSound.HAT;
            case 5 -> NoteBlockSound.GUITAR;
            case 6 -> NoteBlockSound.FLUTE;
            case 7 -> NoteBlockSound.BELL;
            case 8 -> NoteBlockSound.CHIME;
            case 9 -> NoteBlockSound.XYLOPHONE;
            case 10 -> NoteBlockSound.IRON_XYLOPHONE;
            case 11 -> NoteBlockSound.COW_BELL;
            case 12 -> NoteBlockSound.DIDGERIDOO;
            case 13 -> NoteBlockSound.BIT;
            case 14 -> NoteBlockSound.BANJO;
            case 15 -> NoteBlockSound.PLING;
            default -> NoteBlockSound.HARP;
        };
    }

    private static short readShort(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    private static int readInt(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        int byte3 = dis.readUnsignedByte();
        int byte4 = dis.readUnsignedByte();
        return byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24);
    }

    private static String readString(DataInputStream dis) throws IOException {
        int length = readInt(dis);
        if (length == 0) return "";

        byte[] bytes = new byte[length];
        dis.readFully(bytes);
        return new String(bytes, "UTF-8");
    }
}