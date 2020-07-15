package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.MathUtil;

/**
 * ModelDimensionalPocket - ph7
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelDimensionalPocket extends ModelBiped
{
    public ModelRenderer bipedBodyWear;

    public ModelDimensionalPocket()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.bipedBodyWear = new ModelRenderer(this, 0, 0);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.26F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.bipedBodyWear.rotateAngleX = this.bipedBody.rotateAngleX;
        this.bipedBodyWear.rotateAngleY = this.bipedBody.rotateAngleY;
        this.bipedBodyWear.rotateAngleZ = this.bipedBody.rotateAngleZ;
        if (this.isSneak)
        {
            this.bipedBodyWear.rotationPointY += 1.0F;
            this.bipedBodyWear.rotationPointZ -= 0.5F;
        }
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
