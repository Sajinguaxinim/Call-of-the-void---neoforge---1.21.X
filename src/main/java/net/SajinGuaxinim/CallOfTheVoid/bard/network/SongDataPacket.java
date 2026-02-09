package net.SajinGuaxinim.CallOfTheVoid.bard.network;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public record SongDataPacket(
        String songId,
        String name,
        String author,
        int tempo,
        List<NoteData> notes
) implements CustomPacketPayload {

    public static final Type<SongDataPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "song_data"));

    public static final StreamCodec<FriendlyByteBuf, SongDataPacket> STREAM_CODEC =
            StreamCodec.of(SongDataPacket::encode, SongDataPacket::decode);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void encode(FriendlyByteBuf buf, SongDataPacket packet)
    {
        buf.writeUtf(packet.songId);
        buf.writeUtf(packet.name);
        buf.writeUtf(packet.author);
        buf.writeInt(packet.tempo);

        buf.writeInt(packet.notes.size());
        for (NoteData note : packet.notes) {
            buf.writeInt(note.tick());
            buf.writeInt(note.note());
            buf.writeEnum(note.instrument());
            buf.writeFloat(note.volume());
        }
    }

    public static SongDataPacket decode(FriendlyByteBuf buf) {
        String songId = buf.readUtf();
        String name = buf.readUtf();
        String author = buf.readUtf();
        int tempo = buf.readInt();

        int noteCount = buf.readInt();
        List<NoteData> notes = new ArrayList<>();
        for (int i = 0; i < noteCount; i++) {
            int tick = buf.readInt();
            int note = buf.readInt();
            NoteBlockSound instrument = buf.readEnum(NoteBlockSound.class);
            float volume = buf.readFloat();

            notes.add(new NoteData(tick, note, instrument, volume));
        }

        return new SongDataPacket(songId, name, author, tempo, notes);
    }

    public static void handle(SongDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // Converte NoteData para MusicNote
            List<MusicNote> musicNotes = new ArrayList<>();
            for (NoteData noteData : packet.notes) {
                musicNotes.add(new MusicNote(
                        noteData.tick(),
                        noteData.note(),
                        noteData.instrument(),
                        noteData.volume()
                ));
            }

            // Registra a música localmente
            BardSong song = new BardSong(packet.name, packet.author, packet.tempo, musicNotes);
            SongRegistry.registerCustomSong(packet.songId, song);

            CallOfTheVoid.LOGGER.info("Received song data for: {}", packet.name);
        });
    }

    public record NoteData(int tick, int note, NoteBlockSound instrument, float volume) {}

    public static SongDataPacket fromSong(String songId, BardSong song) {
        List<NoteData> noteDataList = new ArrayList<>();
        for (MusicNote note : song.getNotes()) {
            noteDataList.add(new NoteData(
                    note.getTick(),
                    note.getNote(),
                    note.getInstrument(),
                    note.getVolume()
            ));
        }

        return new SongDataPacket(songId, song.getName(), song.getAuthor(), song.getTempo(), noteDataList);
    }
}