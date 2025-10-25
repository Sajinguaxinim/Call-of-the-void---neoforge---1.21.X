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

    public static final Supplier<EntityType<MinionAbobora>> MinionAbobora =
            ENTITY_TYPES.register("MinionAbobora", () -> EntityType.Builder.of(net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora::new, MobCategory.CREATURE)
                    .sized(1, 1).build("minionAbobora"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

