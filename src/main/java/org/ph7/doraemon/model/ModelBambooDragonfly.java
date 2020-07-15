package org.ph7.doraemon.model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.MathUtil;

/**
 * zhuqingt - Undefined
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelBambooDragonfly extends ModelBiped
{
    public ModelRenderer shape1;
    public ModelRenderer shape2;

    public ModelBambooDragonfly()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.shape1.addBox(-5.5F, 0.0F, -1.0F, 11, 0, 2, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-0.5F, -13.0F, -0.5F, 1, 5, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        float angleX = headPitch * 0.017453292F;
        float angleY = netHeadYaw * 0.017453292F;
        this.shape2.rotateAngleX = angleX;
        this.shape2.rotateAngleY = angleY;
        double[] dd = MathUtil.rotateX(-headPitch, 0F, -13.0F, 0F);
        dd = MathUtil.rotateY(-netHeadYaw, dd[0], dd[1], dd[2]);
        this.shape1.rotationPointX = (float) dd[0];
        this.shape1.rotationPointY = (float) dd[1];
        this.shape1.rotationPointZ = (float) dd[2];
        this.shape1.rotateAngleY = ageInTicks * 2;
        if (this.isSneak)
        {
            this.shape1.rotationPointY += 1.0F;
            this.shape2.rotationPointY += 1.0F;
        }
        GlStateManager.pushMatrix();
        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
        }
        else
        {
            if (entity.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
        }
        this.shape1.render(scale);
        this.shape2.render(scale);
        GlStateManager.popMatrix();


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
