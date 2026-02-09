package net.SajinGuaxinim.CallOfTheVoid.event;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.client.KeyBindings;
import net.SajinGuaxinim.CallOfTheVoid.entity.client.MinionAboboraModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;


@EventBusSubscriber(
        modid = CallOfTheVoid.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientModBusEvents {
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.TOGGLE_MODE);
        event.register(KeyBindings.CYCLE_SONG);
        event.register(KeyBindings.OPEN_MINIGAME);
    }
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MinionAboboraModel.LAYER_LOCATION, MinionAboboraModel::createBodyLayer);
    }
}
