package net.SajinGuaxinim.CallOfTheVoid.bard.network;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.item.BardInstrumentItem;
import net.SajinGuaxinim.CallOfTheVoid.bard.mode.BardMode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ChangeModePacket(BardMode mode) implements CustomPacketPayload {

    public static final Type<ChangeModePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "change_mode"));

    public static final StreamCodec<FriendlyByteBuf, ChangeModePacket> STREAM_CODEC =
            StreamCodec.composite(
                    StreamCodec.of(
                            (buf, mode) -> buf.writeEnum(mode),
                            buf -> buf.readEnum(BardMode.class)
                    ),
                    ChangeModePacket::mode,
                    ChangeModePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ChangeModePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            ItemStack mainHand = player.getMainHandItem();
            ItemStack offHand = player.getOffhandItem();

            ItemStack instrumentStack = null;
            if (mainHand.getItem() instanceof BardInstrumentItem) {
                instrumentStack = mainHand;
            } else if (offHand.getItem() instanceof BardInstrumentItem) {
                instrumentStack = offHand;
            }

            if (instrumentStack != null) {
                BardInstrumentItem.setMode(instrumentStack, packet.mode());
            }
        });
    }
}