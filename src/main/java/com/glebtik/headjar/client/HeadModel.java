package com.glebtik.headjar.client;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HeadModel extends ModelBase {
    private final ModelRenderer bb_main;

	public HeadModel() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		//head!!
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, false));
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}
	public void setRotationAngle(float x, float y, float z) {
		bb_main.rotateAngleX = x;
		bb_main.rotateAngleY = y;
		bb_main.rotateAngleZ = z;
	}
}
