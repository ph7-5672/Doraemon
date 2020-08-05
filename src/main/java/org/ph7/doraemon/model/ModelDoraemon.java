package org.ph7.doraemon.model;// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDoraemon extends ModelBase
{
	private final ModelRenderer bb_main;

	public ModelDoraemon()
	{
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -34.2F, -7.5F, 16, 13, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 29, -6.0F, -20.6F, -5.5F, 12, 12, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 0, 2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 48, -10.8F, -20.6F, -2.0F, 4, 12, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 6.7F, -20.6F, -2.0F, 4, 12, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 29, -5.9F, -8.0F, -2.0F, 4, 8, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}