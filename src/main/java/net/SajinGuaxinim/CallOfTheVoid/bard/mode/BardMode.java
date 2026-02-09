package net.SajinGuaxinim.CallOfTheVoid.bard.mode;

import net.SajinGuaxinim.CallOfTheVoid.bard.armor.BardArmorItem;
import net.minecraft.world.entity.player.Player;

public enum BardMode {
    BUFF("Modo Suporte", "Fornece buffs em área"),
    ATTACK("Modo Ataque", "Causa dano com música"),
    HYBRID("Modo Híbrido", "Buff + Ataque (requer set completo)");

    private final String name;
    private final String description;

    BardMode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    /* antigo next()
    public BardMode next() {
        return values()[(this.ordinal() + 1) % values().length];
    } */

    public BardMode next(Player player) {
        BardMode nextMode = values()[(this.ordinal() + 1) % values().length];

        // Pula HYBRID se não tiver set completo
        if (nextMode == HYBRID && !BardArmorItem.hasFullSet(player)) {
            nextMode = values()[(nextMode.ordinal() + 1) % values().length];
        }

        return nextMode;
    }
}