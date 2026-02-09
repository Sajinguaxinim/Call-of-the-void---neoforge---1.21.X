package net.SajinGuaxinim.CallOfTheVoid.bard.item;

import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicType;
import net.minecraft.world.item.Item;

public class HarpItem extends BardInstrumentItem {
    private static final MusicType[] HARP_SONGS = {
            MusicType.FORTUNE_MELODY,
            MusicType.CURSE_CHANT,
            MusicType.SHADOW_REQUIEM
    };

    public HarpItem(Item.Properties properties) {
        super(properties, 12, 8, HARP_SONGS); // Raio 12, Dano 8
    }

    @Override
    public MusicType[] getAvailableSongs() {
        return HARP_SONGS;
    }
}