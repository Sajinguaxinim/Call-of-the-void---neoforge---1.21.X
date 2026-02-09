package net.SajinGuaxinim.CallOfTheVoid.bard.item;

import net.SajinGuaxinim.CallOfTheVoid.bard.armor.BardArmorItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.effect.AreaEffectHandler;
import net.SajinGuaxinim.CallOfTheVoid.bard.minigame.StreakTracker;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.BardSong;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicPlayer;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.SongRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.SajinGuaxinim.CallOfTheVoid.bard.mode.BardMode;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicType;

public class BardInstrumentItem extends Item {
    private final int baseRadius;
    private final int baseDamage;
    private final MusicType[] availableSongs;

    public BardInstrumentItem(Properties properties, int baseRadius, int baseDamage, MusicType[] songs) {
        super(properties.stacksTo(1).durability(500));
        this.baseRadius = baseRadius;
        this.baseDamage = baseDamage;
        this.availableSongs = songs;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Inicia o uso contínuo (permite movimento)
        player.startUsingItem(hand);

        if (player.isShiftKeyDown()) {
            // Shift + Click = Tocar música customizada
            ResourceLocation songId = getSongId(stack);
            BardSong song = SongRegistry.getSong(songId);

            if (song != null && level instanceof ServerLevel serverLevel) {
                MusicPlayer.startPlaying(player, song);
                return InteractionResultHolder.success(stack);
            }
        }

        if (!level.isClientSide) {
            BardMode mode = getCurrentMode(stack);
            MusicType song = getCurrentSong(stack);

            if (mode == BardMode.BUFF || mode == BardMode.HYBRID) {
                applyBuffs(level, player, song);
            }

            if (mode == BardMode.ATTACK || mode == BardMode.HYBRID) {
                performAttack(level, player, song);
            }
        }


        return InteractionResultHolder.consume(stack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW; // Permite movimento enquanto usa
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return 72000; // Uso contínuo
    }

    // ============================================
    // MÉTODOS AUXILIARES - USANDO DATA COMPONENTS
    // ============================================

    public static BardMode getCurrentMode(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        int modeOrdinal = data.copyTag().getInt("BardMode");
        return BardMode.values()[modeOrdinal];
    }

    public static void setMode(ItemStack stack, BardMode mode) {
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> 
            customData.update(tag -> tag.putInt("BardMode", mode.ordinal()))
        );
    }

    public static MusicType getCurrentSong(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        int songOrdinal = data.copyTag().getInt("CurrentSong");
        if (songOrdinal >= 0 && songOrdinal < MusicType.values().length) {
            return MusicType.values()[songOrdinal];
        }
        return MusicType.values()[0]; // Fallback para primeira música
    }

    public static void cycleSong(ItemStack stack, MusicType[] availableSongs) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        int current = data.copyTag().getInt("CurrentSong");
        int next = (current + 1) % availableSongs.length;
        
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> 
            customData.update(tag -> tag.putInt("CurrentSong", next))
        );
    }

    // Método para obter ResourceLocation da música customizada
    private static ResourceLocation getSongId(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        String songPath = data.copyTag().getString("CustomSongId");
        if (songPath != null && !songPath.isEmpty()) {
            return ResourceLocation.parse(songPath);
        }
        return null;
    }

    // Método para definir música customizada
    public static void setSongId(ItemStack stack, ResourceLocation songId) {
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> 
            customData.update(tag -> tag.putString("CustomSongId", songId.toString()))
        );
    }

    // ============================================
    // LÓGICA DE BUFF E ATAQUE
    // ============================================

    private void applyBuffs(Level level, Player player, MusicType song) {
        float streakMultiplier = StreakTracker.getStreakMultiplier(player);
        float armorMultiplier = BardArmorItem.getSetBonusMultiplier(player, BardArmorItem.SetBonusType.RANGE);

        int finalRadius = (int) (baseRadius * streakMultiplier * armorMultiplier);

        int bonusDuration = StreakTracker.hasStreakBonus(player) ? 600 : 0;
        float durationMultiplier = BardArmorItem.getSetBonusMultiplier(player, BardArmorItem.SetBonusType.DURATION);
        bonusDuration = (int) (bonusDuration * durationMultiplier);

        AreaEffectHandler.applyEffectsInRadius(
                level,
                player.position(),
                finalRadius,
                song,
                player,
                bonusDuration
        );

        if (StreakTracker.hasStreakBonus(player)) {
            player.displayClientMessage(
                    Component.literal("⚡ Streak: " + StreakTracker.getStreak(player) + " ⚡"),
                    true
            );
        }

        if (BardArmorItem.hasFullSet(player)) {
            player.displayClientMessage(
                    Component.literal("♪ Set Completo Ativo ♪"),
                    true
            );
        }
    }

    private void performAttack(Level level, Player player, MusicType song) {
        float streakMultiplier = StreakTracker.getStreakMultiplier(player);
        float armorMultiplier = BardArmorItem.getSetBonusMultiplier(player, BardArmorItem.SetBonusType.DAMAGE);

        float finalDamage = baseDamage * streakMultiplier * armorMultiplier;

        AreaEffectHandler.damageInRadius(
                level,
                player.position(),
                baseRadius,
                finalDamage,
                song,
                player
        );
    }

    public MusicType[] getAvailableSongs() {
        return availableSongs;
    }
}
