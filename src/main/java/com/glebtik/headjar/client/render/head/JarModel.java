package com.glebtik.headjar.client.render.head;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class JarModel extends ModelBase {
	public final ModelRenderer bb_main;

	public JarModel() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -10.0F, -5.0F, 10, 9, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 22, 28, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F, false));
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