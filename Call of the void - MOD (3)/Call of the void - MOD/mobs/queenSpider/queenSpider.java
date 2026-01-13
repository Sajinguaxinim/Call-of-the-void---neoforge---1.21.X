// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class queenSpider<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "queenspider"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart corpohumano;
	private final ModelPart caveira;
	private final ModelPart corpoaranha;
	private final ModelPart legs;
	private final ModelPart front_leg_left;
	private final ModelPart front_leg_lef_lower;
	private final ModelPart middle_leg_left;
	private final ModelPart middle_leg_left_lower;
	private final ModelPart bottom_leg_left;
	private final ModelPart bottom_leg_left_lower;
	private final ModelPart front_leg_right;
	private final ModelPart front_leg_right_lower;
	private final ModelPart middle_leg_right;
	private final ModelPart middle_leg_right_lower;
	private final ModelPart bottom_leg_right;
	private final ModelPart bottom_leg_right_lower;

	public queenSpider(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.corpohumano = this.body.getChild("corpohumano");
		this.caveira = this.corpohumano.getChild("caveira");
		this.corpoaranha = this.body.getChild("corpoaranha");
		this.legs = this.body.getChild("legs");
		this.front_leg_left = this.legs.getChild("front_leg_left");
		this.front_leg_lef_lower = this.front_leg_left.getChild("front_leg_lef_lower");
		this.middle_leg_left = this.legs.getChild("middle_leg_left");
		this.middle_leg_left_lower = this.middle_leg_left.getChild("middle_leg_left_lower");
		this.bottom_leg_left = this.legs.getChild("bottom_leg_left");
		this.bottom_leg_left_lower = this.bottom_leg_left.getChild("bottom_leg_left_lower");
		this.front_leg_right = this.legs.getChild("front_leg_right");
		this.front_leg_right_lower = this.front_leg_right.getChild("front_leg_right_lower");
		this.middle_leg_right = this.legs.getChild("middle_leg_right");
		this.middle_leg_right_lower = this.middle_leg_right.getChild("middle_leg_right_lower");
		this.bottom_leg_right = this.legs.getChild("bottom_leg_right");
		this.bottom_leg_right_lower = this.bottom_leg_right.getChild("bottom_leg_right_lower");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 29).addBox(-2.0F, -21.662F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(12, 37).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.5F, 3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -15.0F, -1.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 37).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -15.0F, -1.0F));

		PartDefinition corpohumano = body.addOrReplaceChild("corpohumano", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition cube_r1 = corpohumano.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 20).addBox(-3.0F, -9.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 37).addBox(-2.0F, -5.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 0.6576F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r2 = corpohumano.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 30).addBox(-2.5F, -1.0F, -3.6576F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 1.6576F, 0.1745F, 0.0F, 0.0F));

		PartDefinition caveira = corpohumano.addOrReplaceChild("caveira", CubeListBuilder.create().texOffs(44, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -2.0F, 0.3491F, -0.6981F, 0.0F));

		PartDefinition corpoaranha = body.addOrReplaceChild("corpoaranha", CubeListBuilder.create().texOffs(0, 20).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 1.0F));

		PartDefinition cube_r3 = corpoaranha.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -1.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.8415F, 3.8112F, 0.1745F, 0.0F, 0.0F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition front_leg_left = legs.addOrReplaceChild("front_leg_left", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -0.5F, 0.5F, 0.0872F, 0.0019F, 0.131F));

		PartDefinition cube_r4 = front_leg_left.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(46, 20).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2706F, 1.6586F, -2.1892F, 0.0F, 0.3927F, 0.2182F));

		PartDefinition front_leg_lef_lower = front_leg_left.addOrReplaceChild("front_leg_lef_lower", CubeListBuilder.create(), PartPose.offset(3.5F, 1.5F, -2.0F));

		PartDefinition cube_r5 = front_leg_lef_lower.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 20).addBox(0.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.53F, -1.6227F, -2.1743F, -0.1892F, 0.3574F, -0.4402F));

		PartDefinition cube_r6 = front_leg_lef_lower.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 45).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0323F, -7.3415F, 0.1606F, 0.3444F, -0.1925F, 2.0608F));

		PartDefinition cube_r7 = front_leg_lef_lower.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 37).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9482F, 0.6414F, -0.0307F, 0.0F, 0.3927F, 0.2182F));

		PartDefinition middle_leg_left = legs.addOrReplaceChild("middle_leg_left", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -0.5F, 2.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r8 = middle_leg_left.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 46).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8681F, 1.6809F, -2.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition middle_leg_left_lower = middle_leg_left.addOrReplaceChild("middle_leg_left_lower", CubeListBuilder.create(), PartPose.offset(3.3109F, 2.0F, -2.0F));

		PartDefinition cube_r9 = middle_leg_left_lower.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 28).addBox(0.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -2.6381F, 0.5F, 0.0F, 0.0F, -0.4874F));

		PartDefinition cube_r10 = middle_leg_left_lower.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 37).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4205F, -7.6387F, 0.5F, 0.0F, 0.0F, 1.9776F));

		PartDefinition cube_r11 = middle_leg_left_lower.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(38, 29).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4902F, 0.1138F, 0.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition bottom_leg_left = legs.addOrReplaceChild("bottom_leg_left", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -0.5F, 3.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r12 = bottom_leg_left.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 46).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7978F, 1.6554F, -2.0774F, 0.0F, -0.4363F, 0.2182F));

		PartDefinition bottom_leg_left_lower = bottom_leg_left.addOrReplaceChild("bottom_leg_left_lower", CubeListBuilder.create(), PartPose.offset(2.7248F, 1.3804F, -0.5F));

		PartDefinition cube_r13 = bottom_leg_left_lower.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(44, 0).addBox(0.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -2.0F, 3.131F, 0.1854F, -0.4211F, -0.4869F));

		PartDefinition cube_r14 = bottom_leg_left_lower.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 44).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3403F, -7.1696F, 0.2896F, -0.4331F, 0.1158F, 1.9799F));

		PartDefinition cube_r15 = bottom_leg_left_lower.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(40, 37).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.57F, 0.5758F, 0.1742F, 0.0F, -0.4363F, 0.2182F));

		PartDefinition front_leg_right = legs.addOrReplaceChild("front_leg_right", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -1.5F, 1.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r16 = front_leg_right.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(46, 22).addBox(-3.3866F, -0.4566F, -0.0938F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8093F, 2.5259F, -2.1892F, 0.0F, -0.3927F, -0.5672F));

		PartDefinition front_leg_right_lower = front_leg_right.addOrReplaceChild("front_leg_right_lower", CubeListBuilder.create(), PartPose.offset(-7.5F, 4.5F, -3.1892F));

		PartDefinition cube_r17 = front_leg_right_lower.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(44, 8).addBox(-11.4748F, 1.6719F, 0.4421F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6907F, -1.9741F, 1.0F, -0.1892F, -0.3574F, 0.0911F));

		PartDefinition cube_r18 = front_leg_right_lower.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(8, 44).addBox(7.1843F, -8.1753F, 0.4129F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6907F, -1.9741F, 1.0F, 0.3444F, 0.1925F, -2.4099F));

		PartDefinition cube_r19 = front_leg_right_lower.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(4, 44).addBox(-4.3866F, -6.4566F, -0.0938F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6907F, -1.9741F, 1.0F, 0.0F, -0.3927F, -0.5672F));

		PartDefinition middle_leg_right = legs.addOrReplaceChild("middle_leg_right", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -0.5F, 2.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r20 = middle_leg_right.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(46, 24).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8681F, 1.6809F, -2.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition middle_leg_right_lower = middle_leg_right.addOrReplaceChild("middle_leg_right_lower", CubeListBuilder.create(), PartPose.offset(-3.3109F, 1.5F, -2.0F));

		PartDefinition cube_r21 = middle_leg_right_lower.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(44, 44).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -2.1381F, 0.5F, 0.0F, 0.0F, 0.4874F));

		PartDefinition cube_r22 = middle_leg_right_lower.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(36, 37).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4205F, -7.1387F, 0.5F, 0.0F, 0.0F, -1.9776F));

		PartDefinition cube_r23 = middle_leg_right_lower.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(44, 36).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4902F, 0.6138F, 0.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition bottom_leg_right = legs.addOrReplaceChild("bottom_leg_right", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -0.5F, 3.5F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r24 = bottom_leg_right.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(46, 26).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7978F, 1.6554F, -2.0774F, 0.0F, 0.4363F, -0.2182F));

		PartDefinition bottom_leg_right_lower = bottom_leg_right.addOrReplaceChild("bottom_leg_right_lower", CubeListBuilder.create(), PartPose.offset(-2.7248F, 1.3804F, -1.0F));

		PartDefinition cube_r25 = bottom_leg_right_lower.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(36, 45).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -2.0F, 3.631F, 0.1854F, 0.4211F, 0.4869F));

		PartDefinition cube_r26 = bottom_leg_right_lower.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(32, 45).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3403F, -7.1696F, 0.7896F, -0.4331F, -0.1158F, -1.9799F));

		PartDefinition cube_r27 = bottom_leg_right_lower.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(28, 45).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.57F, 0.5758F, 0.6742F, 0.0F, 0.4363F, -0.2182F));

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