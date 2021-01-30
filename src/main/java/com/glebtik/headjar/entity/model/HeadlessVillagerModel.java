package com.glebtik.headjar.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class HeadlessVillagerModel extends ModelBase {
    /** The body of the VillagerModel */
    public ModelRenderer villagerBody;
    /** The arms of the VillagerModel */
    public ModelRenderer villagerArms;
    /** The right leg of the VillagerModel */
    public ModelRenderer rightVillagerLeg;
    /** The left leg of the VillagerModel */
    public ModelRenderer leftVillagerLeg;

    public HeadlessVillagerModel(float scale) {
        this(scale, 0.0F, 64, 64);
    }

    public HeadlessVillagerModel(float scale, float p_i1164_2_, int width, int height)
    {
        this.villagerBody = (new ModelRenderer(this)).setTextureSize(width, height);
        this.villagerBody.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.villagerBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, scale);
        this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, scale + 0.5F);
        this.villagerArms = (new ModelRenderer(this)).setTextureSize(width, height);
        this.villagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
        this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
        this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
        this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);
        this.rightVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(width, height);
        this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.leftVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(width, height);
        this.leftVillagerLeg.mirror = true;
        this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.villagerBody.render(scale);
        this.rightVillagerLeg.render(scale);
        this.leftVillagerLeg.render(scale);
        this.villagerArms.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.villagerArms.rotationPointY = 3.0F;
        this.villagerArms.rotationPointZ = -1.0F;
        this.villagerArms.rotateAngleX = -0.75F;
        this.rightVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leftVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.rightVillagerLeg.rotateAngleY = 0.0F;
        this.leftVillagerLeg.rotateAngleY = 0.0F;
    }
}