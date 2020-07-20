package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelScarecrow - Undefined
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelScarecrow extends ModelBase
{
    public ModelRenderer leg;
    public ModelRenderer body;
    public ModelRenderer arm;
    public ModelRenderer head;

    public ModelScarecrow()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leg = new ModelRenderer(this, 0, 0);
        this.leg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leg.addBox(-1.0F, -16.0F, -1.0F, 2, 16, 2, 0.0F);
        this.arm = new ModelRenderer(this, 11, 0);
        this.arm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arm.addBox(-12.0F, -23.0F, -1.0F, 24, 2, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-6.0F, -23.0F, -6.0F, 12, 12, 12, 0.0F);
        this.head = new ModelRenderer(this, 0, 40);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-6.0F, -35.0F, -6.0F, 12, 12, 12, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.leg.render(f5);
        this.arm.render(f5);
        this.body.render(f5);
        this.head.render(f5);
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
