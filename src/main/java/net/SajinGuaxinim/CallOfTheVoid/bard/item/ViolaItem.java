package net.SajinGuaxinim.CallOfTheVoid.bard.item;

import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicType;
import net.minecraft.world.item.Item;

public class ViolaItem extends BardInstrumentItem {
    private static final MusicType[] VIOLA_SONGS = {
            MusicType.HEALING_HYMN,
            MusicType.VITALITY_SONG,
            MusicType.WAR_DRUMS
    };

    public ViolaItem(Item.Properties properties) {
        super(properties, 8, 6, VIOLA_SONGS); // Raio 8, Dano 6
    }

    @Override
    public MusicType[] getAvailableSongs() {
        return VIOLA_SONGS;
    }
}