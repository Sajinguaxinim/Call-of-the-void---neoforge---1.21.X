package net.SajinGuaxinim.CallOfTheVoid.item;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.armor.BardArmorItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.armor.BardArmorMaterial;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.FluteItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.HarpItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.ViolaItem;
import net.SajinGuaxinim.CallOfTheVoid.entity.ModEntities;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CallOfTheVoid.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MINION_ABOBORA_EGG = ITEMS.register("minion_abobora_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MINION_ABOBORA, 0x31afaf, 0xffac00,
                    new Item.Properties()));



    // ===== B A R D O =====

    // === INSTRUMENTOS DE BARDO ===
    public static final DeferredItem<Item> VIOLA = ITEMS.register("viola",
            () -> new ViolaItem(new Item.Properties()));

    public static final DeferredItem<Item> FLUTE = ITEMS.register("flute",
            () -> new FluteItem(new Item.Properties()));

    public static final DeferredItem<Item> HARP = ITEMS.register("harp",
            () -> new HarpItem(new Item.Properties()));

    // === ARMADURAS DE BARDO ===
    public static final DeferredItem<Item> BARD_HELMET = ITEMS.register("bard_helmet",
            () -> new BardArmorItem(BardArmorMaterial.BARD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final DeferredItem<Item> BARD_CHESTPLATE = ITEMS.register("bard_chestplate",
            () -> new BardArmorItem(BardArmorMaterial.BARD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final DeferredItem<Item> BARD_LEGGINGS = ITEMS.register("bard_leggings",
            () -> new BardArmorItem(BardArmorMaterial.BARD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final DeferredItem<Item> BARD_BOOTS = ITEMS.register("bard_boots",
            () -> new BardArmorItem(BardArmorMaterial.BARD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ===== B A R D O =====

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
