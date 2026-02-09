package net.SajinGuaxinim.CallOfTheVoid.bard.client;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.BardInstrumentItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.minigame.RhythmMinigame;
import net.SajinGuaxinim.CallOfTheVoid.bard.mode.BardMode;
import net.SajinGuaxinim.CallOfTheVoid.bard.network.ChangeModePacket;
import net.SajinGuaxinim.CallOfTheVoid.bard.network.CycleSongPacket;
import net.SajinGuaxinim.CallOfTheVoid.bard.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = CallOfTheVoid.MOD_ID, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null) return;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        // Verifica se está segurando um instrumento
        boolean holdingInstrument = mainHand.getItem() instanceof BardInstrumentItem ||
                offHand.getItem() instanceof BardInstrumentItem;

        if (!holdingInstrument) return;

        ItemStack instrumentStack = mainHand.getItem() instanceof BardInstrumentItem ? mainHand : offHand;

        // Trocar Modo (R)
        if (KeyBindings.TOGGLE_MODE.consumeClick()) {
            BardMode currentMode = BardInstrumentItem.getCurrentMode(instrumentStack);
            BardMode nextMode = currentMode.next(player);

            // Envia pacote para o servidor
            NetworkHandler.sendToServer(new ChangeModePacket(nextMode));

            player.displayClientMessage(
                    Component.literal("Modo: ").append(Component.literal(nextMode.getName())),
                    true // actionbar
            );
        }

        // Trocar Música (V)
        if (KeyBindings.CYCLE_SONG.consumeClick()) {
            // Envia pacote para o servidor
            NetworkHandler.sendToServer(new CycleSongPacket());

            player.displayClientMessage(
                    Component.literal("Música alterada!"),
                    true
            );
        }

        // Abrir Minigame (G)
        if (KeyBindings.OPEN_MINIGAME.consumeClick()) {
            if (player.getMainHandItem().getItem() instanceof BardInstrumentItem ||
                    player.getOffhandItem().getItem() instanceof BardInstrumentItem) {

                RhythmMinigame minigame = new RhythmMinigame(player);
                mc.setScreen(new net.SajinGuaxinim.CallOfTheVoid.bard.client.gui.RhythmGameScreen(minigame));
            }
        }
    }
}