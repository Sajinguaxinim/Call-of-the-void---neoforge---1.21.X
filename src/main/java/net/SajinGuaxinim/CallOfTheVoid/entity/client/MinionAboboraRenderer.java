package net.SajinGuaxinim.CallOfTheVoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;

public class MinionAboboraRenderer extends MobRenderer<MinionAbobora, MinionAboboraModel<MinionAbobora>> {
    public MinionAboboraRenderer(EntityRendererProvider.Context context) {
        super(context, new MinionAboboraModel<>(context.bakeLayer(MinionAboboraModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(MinionAbobora minionAbobora) {
        return ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "textures/entity/MinionAbobora/minionAbobora.png");
    }

    @Override
    public void render(MinionAbobora entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {


        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
