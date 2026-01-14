/*package net.SajinGuaxinim.CallOfTheVoid.entity;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.minion_abobora;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CallOfTheVoid.MOD_ID);

    public static final Supplier<EntityType<minion_abobora>> minion_abobora =
            ENTITY_TYPES.register("minionabobora", () -> EntityType.Builder.of(net.SajinGuaxinim.CallOfTheVoid.entity.custom.minion_abobora::new, MobCategory.CREATURE)
                    .sized(1, 1).build("minionabobora"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}*/

package net.SajinGuaxinim.CallOfTheVoid.entity;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CallOfTheVoid.MOD_ID);

    // Constant-style field name (uppercase with underscores)
    public static final Supplier<EntityType<MinionAbobora>> MINION_ABOBORA =
            ENTITY_TYPES.register("minionabobora",
                    () -> EntityType.Builder.of(MinionAbobora::new, MobCategory.CREATURE)
                            .sized(1.0F, 1.0F)
                            .build("minionabobora"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}