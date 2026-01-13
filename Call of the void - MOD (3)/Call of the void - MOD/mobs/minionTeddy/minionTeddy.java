// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class minionTeddy<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "minionteddy"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart bracos;
	private final ModelPart BD;
	private final ModelPart BE;
	private final ModelPart pernas;
	private final ModelPart PD;
	private final ModelPart PE;

	public minionTeddy(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.torso = this.body.getChild("torso");
		this.bracos = this.body.getChild("bracos");
		this.BD = this.bracos.getChild("BD");
		this.BE = this.bracos.getChild("BE");
		this.pernas = this.body.getChild("pernas");
		this.PD = this.pernas.getChild("PD");
		this.PE = this.pernas.getChild("PE");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -2.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 15).addBox(-1.0F, -2.0F, -3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 5).addBox(-3.0F, -5.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 18).addBox(1.0F, -5.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 1.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition bracos = body.addOrReplaceChild("bracos", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition BD = bracos.addOrReplaceChild("BD", CubeListBuilder.create().texOffs(14, 8).addBox(0.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -2.0F, 0.0F));

		PartDefinition BE = bracos.addOrReplaceChild("BE", CubeListBuilder.create().texOffs(14, 14).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, 0.0F));

		PartDefinition pernas = body.addOrReplaceChild("pernas", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition PD = pernas.addOrReplaceChild("PD", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 1.0F));

		PartDefinition PE = pernas.addOrReplaceChild("PE", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}