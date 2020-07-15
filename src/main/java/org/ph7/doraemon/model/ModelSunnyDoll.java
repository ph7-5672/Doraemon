package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelSunnyDoll - ph7
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelSunnyDoll extends ModelBase 
{
    public ModelRenderer rope;
    public ModelRenderer head;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer body4;

    public ModelSunnyDoll()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rope = new ModelRenderer(this, 0, 0);
        this.rope.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rope.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.head = new ModelRenderer(this, 0, 1);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, 5.0F, -2.0F, 5, 5, 5, 0.25F);
        this.body3 = new ModelRenderer(this, 0, 19);
        this.body3.setRotationPoint(0.0F, 10.0F, -2.0F);
        this.body3.addBox(-2.0F, 0.0F, 0.0F, 5, 8, 0, 0.25F);
        this.setRotateAngle(body3, -0.17453292519943295F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 0, 6);
        this.body1.setRotationPoint(-1.0F, 10.0F, 0.0F);
        this.body1.addBox(-1.0F, 0.0F, -2.0F, 0, 8, 5, 0.25F);
        this.setRotateAngle(body1, 0.0F, 0.0F, 0.17453292519943295F);
        this.body2 = new ModelRenderer(this, 10, 6);
        this.body2.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.body2.addBox(1.0F, 0.0F, -2.0F, 0, 8, 5, 0.25F);
        this.setRotateAngle(body2, 0.0F, 0.0F, -0.17453292519943295F);
        this.body4 = new ModelRenderer(this, 10, 19);
        this.body4.setRotationPoint(0.0F, 10.0F, 3.0F);
        this.body4.addBox(-2.0F, 0.0F, 0.0F, 5, 8, 0, 0.25F);
        this.setRotateAngle(body4, 0.17453292519943295F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.body2.render(f5);
        this.rope.render(f5);
        this.body3.render(f5);
        this.head.render(f5);
        this.body4.render(f5);
        this.body1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
