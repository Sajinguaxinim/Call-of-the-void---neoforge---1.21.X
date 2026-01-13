// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class JackSkelly<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "jackskelly"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart corpo;
	private final ModelPart Pernas;
	private final ModelPart PE;
	private final ModelPart PD;
	private final ModelPart BD;
	private final ModelPart BE;

	public JackSkelly(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.corpo = this.body.getChild("corpo");
		this.Pernas = this.body.getChild("Pernas");
		this.PE = this.Pernas.getChild("PE");
		this.PD = this.Pernas.getChild("PD");
		this.BD = this.body.getChild("BD");
		this.BE = this.body.getChild("BE");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 25).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -23.0F, 0.0F));

		PartDefinition corpo = body.addOrReplaceChild("corpo", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition Pernas = body.addOrReplaceChild("Pernas", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition PE = Pernas.addOrReplaceChild("PE", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition PD = Pernas.addOrReplaceChild("PD", CubeListBuilder.create().texOffs(8, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition BD = body.addOrReplaceChild("BD", CubeListBuilder.create().texOffs(16, 11).addBox(0.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -23.0F, 0.0F));

		PartDefinition BE = body.addOrReplaceChild("BE", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -23.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}