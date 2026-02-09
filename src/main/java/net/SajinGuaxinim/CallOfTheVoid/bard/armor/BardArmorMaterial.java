package net.SajinGuaxinim.CallOfTheVoid.bard.armor;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class BardArmorMaterial {

    public static final Holder<ArmorMaterial> BARD_ARMOR = register("bard_armor",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }),
            15, // Enchantability
            SoundEvents.ARMOR_EQUIP_GOLD,
            () -> Ingredient.of(Items.GOLD_INGOT), // Material de reparo
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "bard")
                    )
            ),
            0.0F, // Toughness
            0.0F  // Knockback resistance
    );

    private static Holder<ArmorMaterial> register(String name,
                                                  EnumMap<ArmorItem.Type, Integer> defense,
                                                  int enchantmentValue,
                                                  Holder<SoundEvent> equipSound,
                                                  Supplier<Ingredient> repairIngredient,
                                                  List<ArmorMaterial.Layer> layers,
                                                  float toughness,
                                                  float knockbackResistance) {

        EnumMap<ArmorItem.Type, Integer> defenseMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            defenseMap.put(type, defense.get(type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, name),
                new ArmorMaterial(
                        defenseMap,
                        enchantmentValue,
                        equipSound,
                        repairIngredient,
                        layers,
                        toughness,
                        knockbackResistance
                )
        );
    }
}