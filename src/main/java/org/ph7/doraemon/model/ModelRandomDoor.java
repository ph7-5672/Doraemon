package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.entity.EntityRandomDoor;

/**
 * EntityRandomDoor - ph7
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelRandomDoor extends ModelBase
{
    public ModelRenderer frameTop;
    public ModelRenderer frameLeft;
    public ModelRenderer frameRight;
    public ModelRenderer frameBottom;
    public ModelRenderer top;
    public ModelRenderer door;

    public ModelRandomDoor()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.door = new ModelRenderer(this, 4, 1);
        this.door.setRotationPoint(8.5F, -11.0F, 0.5F);
        this.door.addBox(-15.5F, -15.0F, -0.5F, 15, 30, 1, 0.3F);
        this.frameRight = new ModelRenderer(this, 0, 0);
        this.frameRight.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.frameRight.addBox(8.0F, -16.0F, 0.0F, 1, 33, 1, 0.3F);
        this.frameLeft = new ModelRenderer(this, 0, 0);
        this.frameLeft.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.frameLeft.addBox(-8.0F, -16.0F, 0.0F, 1, 33, 1, 0.3F);
        this.frameTop = new ModelRenderer(this, 0, 0);
        this.frameTop.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.frameTop.addBox(-7.0F, -16.0F, 0.0F, 15, 1, 1, 0.3F);
        this.frameBottom = new ModelRenderer(this, 0, 0);
        this.frameBottom.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.frameBottom.addBox(-7.0F, 15.0F, 0.0F, 15, 1, 1, 0.3F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.top.addBox(-8.5F, -17.0F, 0.0F, 18, 1, 1, 0.3F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entityIn instanceof EntityRandomDoor)
        {
            EntityRandomDoor randomDoor = (EntityRandomDoor) entityIn;
            this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
            if ((randomDoor).isOpen())
            {
                this.door.rotateAngleY = this.top.rotateAngleY + 2;
            }
            else
            {
                this.door.rotateAngleY = this.top.rotateAngleY;
            }
        }
        this.door.render(scale);
        this.frameRight.render(scale);
        this.frameLeft.render(scale);
        this.frameTop.render(scale);
        this.frameBottom.render(scale);
        this.top.render(scale);
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
