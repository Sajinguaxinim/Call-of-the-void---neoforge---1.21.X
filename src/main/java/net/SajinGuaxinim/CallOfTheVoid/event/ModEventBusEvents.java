package net.SajinGuaxinim.CallOfTheVoid.event;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.entity.ModEntities;
import net.SajinGuaxinim.CallOfTheVoid.entity.client.MinionAboboraModel;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = CallOfTheVoid.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MinionAboboraModel.LAYER_LOCATION, MinionAboboraModel::createBodyLayer);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MinionAbobora.get(), MinionAbobora.createAttributes().build());
    }
}
