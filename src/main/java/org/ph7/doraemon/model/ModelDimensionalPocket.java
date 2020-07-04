package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelDimensionalPocket - ph7
 * Created using Tabula 7.1.0
 */
public class ModelDimensionalPocket extends ModelBiped
{
    public ModelRenderer bipedBodyWear;

    public ModelDimensionalPocket()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.bipedBodyWear = new ModelRenderer(this, 0, 0);
        this.bipedBodyWear.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedBodyWear.addBox(-4.0F, -6.0F, -2.0F, 8, 12, 4, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.bipedBodyWear.render(f5);
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
