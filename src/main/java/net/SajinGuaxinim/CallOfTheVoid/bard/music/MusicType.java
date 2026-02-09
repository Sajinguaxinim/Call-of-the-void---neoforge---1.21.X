package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import net.minecraft.ChatFormatting;

public enum MusicType {
    // Músicas Verdes (Suporte/Cura)
    HEALING_HYMN("Hino de Cura", MusicColor.GREEN, new String[]{"regeneration", "instant_health", "absorption"}),
    VITALITY_SONG("Canção da Vitalidade", MusicColor.GREEN, new String[]{"resistance", "fire_resistance", "health_boost"}),
    FORTUNE_MELODY("Melodia da Fortuna", MusicColor.GREEN, new String[]{"luck", "saturation"}),

    // Músicas Vermelhas (Dano/Debuff)
    WAR_DRUMS("Tambores de Guerra", MusicColor.RED, new String[]{"strength", "speed"}),
    CURSE_CHANT("Cântico Amaldiçoado", MusicColor.RED, new String[]{"weakness", "poison", "wither"}),
    SHADOW_REQUIEM("Réquiem das Sombras", MusicColor.RED, new String[]{"darkness", "blindness"}),

    // Músicas Azuis (Utilidade/Mobilidade)
    WIND_SERENADE("Serenata do Vento", MusicColor.BLUE, new String[]{"speed", "jump_boost", "slow_falling"}),
    OCEAN_BALLAD("Balada do Oceano", MusicColor.BLUE, new String[]{"water_breathing", "dolphins_grace"}),
    NIGHT_LULLABY("Canção de Ninar Noturna", MusicColor.BLUE, new String[]{"night_vision", "invisibility"});

    private final String displayName;
    private final MusicColor color;
    private final String[] effects;

    MusicType(String displayName, MusicColor color, String[] effects) {
        this.displayName = displayName;
        this.color = color;
        this.effects = effects;
    }

    public String getDisplayName() { return displayName; }
    public MusicColor getColor() { return color; }
    public String[] getEffects() { return effects; }

    public enum MusicColor {
        GREEN(ChatFormatting.GREEN, 0x00FF00),
        RED(ChatFormatting.RED, 0xFF0000),
        BLUE(ChatFormatting.AQUA, 0x00FFFF);

        private final ChatFormatting formatting;
        private final int hexColor;

        MusicColor(ChatFormatting formatting, int hexColor) {
            this.formatting = formatting;
            this.hexColor = hexColor;
        }

        public ChatFormatting getFormatting() { return formatting; }
        public int getHexColor() { return hexColor; }
    }
}