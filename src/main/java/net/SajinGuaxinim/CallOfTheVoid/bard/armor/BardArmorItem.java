package net.SajinGuaxinim.CallOfTheVoid.bard.armor;

import net.SajinGuaxinim.CallOfTheVoid.bard.mode.BardMode;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BardArmorItem extends ArmorItem {

    public BardArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties.durability(type.getDurability(15)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            // Verifica se tem set completo
            if (hasFullSet(player)) {
                // Permite modo HYBRID
                enableHybridMode(player);
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§6Set de Bardo"));
        tooltipComponents.add(Component.literal("§7Bônus de Set Completo:"));
        tooltipComponents.add(Component.literal("§7- Desbloqueia Modo Híbrido"));
        tooltipComponents.add(Component.literal("§7- +20% de alcance"));
        tooltipComponents.add(Component.literal("§7- +15% de dano"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public static boolean hasFullSet(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return helmet.getItem() instanceof BardArmorItem &&
                chestplate.getItem() instanceof BardArmorItem &&
                leggings.getItem() instanceof BardArmorItem &&
                boots.getItem() instanceof BardArmorItem;
    }

    private void enableHybridMode(Player player) {
        // Lógica para permitir modo HYBRID será implementada no BardInstrumentItem
    }

    public static float getSetBonusMultiplier(Player player, SetBonusType type) {
        if (!hasFullSet(player)) {
            return 1.0f;
        }

        return switch (type) {
            case RANGE -> 1.2f;  // +20% alcance
            case DAMAGE -> 1.15f; // +15% dano
            case DURATION -> 1.25f; // +25% duração
        };
    }

    public enum SetBonusType {
        RANGE,
        DAMAGE,
        DURATION
    }
}