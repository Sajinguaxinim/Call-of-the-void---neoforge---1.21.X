package net.SajinGuaxinim.CallOfTheVoid.bard.network;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(CallOfTheVoid.MOD_ID);

        registrar.playToServer(
                ChangeModePacket.TYPE,
                ChangeModePacket.STREAM_CODEC,
                ChangeModePacket::handle
        );

        registrar.playToServer(
                CycleSongPacket.TYPE,
                CycleSongPacket.STREAM_CODEC,
                CycleSongPacket::handle
        );

        registrar.playToClient(
                SongDataPacket.TYPE,
                SongDataPacket.STREAM_CODEC,
                SongDataPacket::handle
        );
    }

    public static void sendToServer(CustomPacketPayload packet) {
        PacketDistributor.sendToServer(packet);
    }

    public static void sendToPlayer(CustomPacketPayload packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}