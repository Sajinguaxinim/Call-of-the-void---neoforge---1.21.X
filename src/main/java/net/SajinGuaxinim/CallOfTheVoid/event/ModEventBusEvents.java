package net.SajinGuaxinim.CallOfTheVoid.event;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.MusicPlayer;
import net.SajinGuaxinim.CallOfTheVoid.bard.music.SongRegistry;
import net.SajinGuaxinim.CallOfTheVoid.bard.network.NetworkHandler;
import net.SajinGuaxinim.CallOfTheVoid.entity.ModEntities;
import net.SajinGuaxinim.CallOfTheVoid.entity.client.MinionAboboraModel;
import net.SajinGuaxinim.CallOfTheVoid.bard.client.KeyBindings;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
// import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.io.File;

@EventBusSubscriber(modid = CallOfTheVoid.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /*@SubscribeEvent  // ← ADICIONE ESTA ANOTAÇÃO
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MinionAboboraModel.LAYER_LOCATION, MinionAboboraModel::createBodyLayer);
    }*/

    @SubscribeEvent  // ← ADICIONE ESTA ANOTAÇÃO
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MINION_ABOBORA.get(), MinionAbobora.createAttributes().build());
    }

    /*@SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.TOGGLE_MODE);
        event.register(KeyBindings.CYCLE_SONG);
        event.register(KeyBindings.OPEN_MINIGAME);
    }*/

    /*@SubscribeEvent
    public static void onServerTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            MusicPlayer.tick(serverLevel);
        }
    }*/

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Cria pasta de músicas personalizadas
            File songsDir = new File(FMLPaths.CONFIGDIR.get().toFile(), "callofthevoid/songs");
            songsDir.mkdirs();

            // Carrega todas as músicas .nbs da pasta
            SongRegistry.loadAllFromDirectory(songsDir);

            CallOfTheVoid.LOGGER.info("Loaded custom songs from: {}", songsDir.getAbsolutePath());
        });
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        NetworkHandler.register(event);
    }
}