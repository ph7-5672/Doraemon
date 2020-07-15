package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelWallPaper - Undefined
 * Created using Tabula 7.1.0
 */
@SideOnly(Side.CLIENT)
public class ModelWallpaper extends ModelBase
{
    public ModelRenderer wall;

    public ModelWallpaper(int width, int height)
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.wall = new ModelRenderer(this, 0, 0);
        this.wall.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wall.addBox( ((float)width) / -2F, ((float)height) / -2F, 0.0F, width, height, 0, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.wall.render(f5);
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
