package net.SajinGuaxinim.CallOfTheVoid.bard.item;

import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicType;
import net.minecraft.world.item.Item;

public class FluteItem extends BardInstrumentItem {
    private static final MusicType[] FLUTE_SONGS = {
            MusicType.WIND_SERENADE,
            MusicType.OCEAN_BALLAD,
            MusicType.NIGHT_LULLABY
    };

    public FluteItem(Item.Properties properties) {
        super(properties, 10, 4, FLUTE_SONGS); // Raio 10, Dano 4
    }

    @Override
    public MusicType[] getAvailableSongs() {
        return FLUTE_SONGS;
    }
}