package net.SajinGuaxinim.CallOfTheVoid.bard.effect;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicType;

import java.util.List;

public class AreaEffectHandler {

    public static void applyEffectsInRadius(Level level, Vec3 center, double radius, MusicType song, Player caster, int bonusDuration) {
        AABB area = new AABB(center.subtract(radius, radius, radius), center.add(radius, radius, radius));
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);

        for (LivingEntity entity : entities) {
            if (entity.distanceToSqr(center) <= radius * radius) {
                // Aplica apenas em aliados (jogadores ou não-hostis)
                if (isAlly(entity, caster)) {
                    applyMusicEffects(entity, song, bonusDuration);
                }
            }
        }

        // Partículas visuais
        spawnParticles(level, center, radius, song.getColor());
    }

    public static void damageInRadius(Level level, Vec3 center, double radius, float damage, MusicType song, Player caster) {
        AABB area = new AABB(center.subtract(radius, radius, radius), center.add(radius, radius, radius));
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);

        for (LivingEntity entity : entities) {
            if (entity.distanceToSqr(center) <= radius * radius && entity != caster) {
                if (!isAlly(entity, caster)) {
                    entity.hurt(level.damageSources().playerAttack(caster), damage);
                    applyDebuffs(entity, song);
                }
            }
        }
    }

    private static void applyMusicEffects(LivingEntity entity, MusicType song, int bonusDuration) {
        int duration = 20 * 30 + bonusDuration; // 30 segundos + bônus
        int amplifier = 0;

        for (String effectName : song.getEffects()) {
            Holder<MobEffect> effect = getEffectFromName(effectName);
            if (effect != null) {
                entity.addEffect(new MobEffectInstance(effect, duration, amplifier));
            }
        }
    }

    private static void applyDebuffs(LivingEntity entity, MusicType song) {
        int duration = 20 * 15; // 15 segundos
        for (String effectName : song.getEffects()) {
            Holder<MobEffect> effect = getEffectFromName(effectName);
            if (effect != null && isDebuff(effect)) {
                entity.addEffect(new MobEffectInstance(effect, duration, 0));
            }
        }
    }

    // CORRIGIDO: Retorna Holder<MobEffect> para 1.21.x
    private static Holder<MobEffect> getEffectFromName(String name) {
        return switch (name) {
            case "regeneration" -> MobEffects.REGENERATION;
            case "instant_health" -> MobEffects.HEAL;
            case "absorption" -> MobEffects.ABSORPTION;
            case "resistance" -> MobEffects.DAMAGE_RESISTANCE;
            case "fire_resistance" -> MobEffects.FIRE_RESISTANCE;
            case "health_boost" -> MobEffects.HEALTH_BOOST;
            case "luck" -> MobEffects.LUCK;
            case "saturation" -> MobEffects.SATURATION;
            case "strength" -> MobEffects.DAMAGE_BOOST;
            case "speed" -> MobEffects.MOVEMENT_SPEED;
            case "weakness" -> MobEffects.WEAKNESS;
            case "poison" -> MobEffects.POISON;
            case "wither" -> MobEffects.WITHER;
            case "darkness" -> MobEffects.DARKNESS;
            case "blindness" -> MobEffects.BLINDNESS;
            case "jump_boost" -> MobEffects.JUMP;
            case "slow_falling" -> MobEffects.SLOW_FALLING;
            case "water_breathing" -> MobEffects.WATER_BREATHING;
            case "dolphins_grace" -> MobEffects.DOLPHINS_GRACE;
            case "night_vision" -> MobEffects.NIGHT_VISION;
            case "invisibility" -> MobEffects.INVISIBILITY;
            default -> null;
        };
    }

    private static boolean isAlly(LivingEntity entity, Player caster) {
        // Verifica se é aliado
        return entity instanceof Player || entity.getType().getCategory().isFriendly();
    }

    // CORRIGIDO: Recebe Holder<MobEffect>
    private static boolean isDebuff(Holder<MobEffect> effectHolder) {
        return !effectHolder.value().isBeneficial();
    }

    private static void spawnParticles(Level level, Vec3 center, double radius, MusicType.MusicColor color) {
        if (level instanceof ServerLevel serverLevel) {
            // Notas musicais sempre aparecem
            ParticleHandler.spawnMusicNotes(serverLevel, center, radius, 30);

            // Partículas específicas por cor
            switch (color) {
                case GREEN -> ParticleHandler.spawnHealingParticles(serverLevel, center, radius);
                case RED -> ParticleHandler.spawnDamageParticles(serverLevel, center, radius);
                case BLUE -> ParticleHandler.spawnBuffAura(serverLevel, center, radius, 0);
            }
        }
    }
}