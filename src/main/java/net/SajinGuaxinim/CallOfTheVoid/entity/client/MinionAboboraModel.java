package net.SajinGuaxinim.CallOfTheVoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.entity.custom.MinionAbobora;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MinionAboboraModel<T extends MinionAbobora> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "minionAbobora"), "main");
    private final ModelPart corpo;
    private final ModelPart torso;
    private final ModelPart bracos;
    private final ModelPart MD;
    private final ModelPart ME;
    private final ModelPart PD;
    private final ModelPart PE;
    private final ModelPart cabeca;

    public MinionAboboraModel(ModelPart root) {
        this.corpo = root.getChild("corpo");
        this.torso = this.corpo.getChild("torso");
        this.bracos = this.corpo.getChild("bracos");
        this.MD = this.bracos.getChild("MD");
        this.ME = this.bracos.getChild("ME");
        this.PD = this.corpo.getChild("PD");
        this.PE = this.corpo.getChild("PE");
        this.cabeca = this.corpo.getChild("cabeca");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition corpo = partdefinition.addOrReplaceChild("corpo", CubeListBuilder.create(), PartPose.offset(-4.0F, 23.0F, 0.0F));

        PartDefinition torso = corpo.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(20, 17).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, 1.0F));

        PartDefinition bracos = corpo.addOrReplaceChild("bracos", CubeListBuilder.create(), PartPose.offset(0.5001F, -4.3542F, 0.0F));

        PartDefinition MD = bracos.addOrReplaceChild("MD", CubeListBuilder.create(), PartPose.offset(5.9997F, 0.0F, 0.0F));

        PartDefinition MD_r1 = MD.addOrReplaceChild("MD_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, 0.0F, 0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 1.0036F));

        PartDefinition ME = bracos.addOrReplaceChild("ME", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ME_r1 = ME.addOrReplaceChild("ME_r1", CubeListBuilder.create().texOffs(8, 27).addBox(-1.0F, 0.0F, 0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -1.0036F));

        PartDefinition PD = corpo.addOrReplaceChild("PD", CubeListBuilder.create().texOffs(12, 23).addBox(-0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition PE = corpo.addOrReplaceChild("PE", CubeListBuilder.create().texOffs(20, 24).addBox(0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cabeca = corpo.addOrReplaceChild("cabeca", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.1522F, -4.2346F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-2.0F, -5.1522F, -2.2346F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-1.0F, -6.1522F, -1.2346F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.8478F, 0.2346F));

        PartDefinition cube_r1 = cabeca.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 1.7453F, 0.0F, 0.0F));

        PartDefinition cube_r2 = cabeca.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 27).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.1522F, 0.7654F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(MinionAbobora entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(MinionAboboraAnimation.walking, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, MinionAboboraAnimation.idle2, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw,float headPitch) {
        headYaw = Mth.clamp(headYaw, -30,30);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.cabeca.yRot = headYaw * ((float)Math.PI/ 180f);
        this.cabeca.xRot = headPitch * ((float)Math.PI/180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        corpo.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return corpo;
    }
}
