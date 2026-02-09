package net.SajinGuaxinim.CallOfTheVoid.bard.network;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.BardInstrumentItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.ViolaItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.FluteItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.HarpItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CycleSongPacket() implements CustomPacketPayload {

    public static final Type<CycleSongPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "cycle_song"));

    public static final StreamCodec<FriendlyByteBuf, CycleSongPacket> STREAM_CODEC =
            StreamCodec.unit(new CycleSongPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CycleSongPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            ItemStack mainHand = player.getMainHandItem();
            ItemStack offHand = player.getOffhandItem();

            ItemStack instrumentStack = null;
            BardInstrumentItem instrument = null;

            if (mainHand.getItem() instanceof BardInstrumentItem bardItem) {
                instrumentStack = mainHand;
                instrument = bardItem;
            } else if (offHand.getItem() instanceof BardInstrumentItem bardItem) {
                instrumentStack = offHand;
                instrument = bardItem;
            }

            if (instrumentStack != null && instrument != null) {
                BardInstrumentItem.cycleSong(instrumentStack, instrument.getAvailableSongs());
            }
        });
    }
}