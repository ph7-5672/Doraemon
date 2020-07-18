package org.ph7.doraemon.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWeatherBox - ph7
 * Created using Tabula 7.1.0
 */
public class ModelWeatherBox extends ModelBase
{
    public ModelRenderer consoleBehind;
    public ModelRenderer consoleBottom;
    public ModelRenderer consoleRight;
    public ModelRenderer consoleLeft;
    public ModelRenderer consoleTop;
    public ModelRenderer consoleFront;
    public ModelRenderer pillar;
    public ModelRenderer flapper;

    public ModelWeatherBox() 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.consoleLeft = new ModelRenderer(this, 0, 7);
        this.consoleLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.consoleLeft.addBox(-5.0F, -5.0F, -5.0F, 0, 5, 10, 0.0F);
        this.pillar = new ModelRenderer(this, 0, 0);
        this.pillar.setRotationPoint(-0.5F, -6.0F, 7.0F);
        this.pillar.addBox(0.0F, -9.0F, 0.0F, 1, 5, 1, 0.0F);
        this.consoleFront = new ModelRenderer(this, 0, 9);
        this.consoleFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.consoleFront.addBox(-5.0F, -3.0F, -5.0F, 10, 3, 0, 0.0F);
        this.consoleBehind = new ModelRenderer(this, 34, 0);
        this.consoleBehind.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.consoleBehind.addBox(-5.0F, -10.0F, 5.0F, 10, 10, 5, 0.0F);
        this.flapper = new ModelRenderer(this, 0, 0);
        this.flapper.setRotationPoint(0.0F, -10.0F, 8.0F);
        this.flapper.addBox(-4.0F, -4.0F, -1.0F, 8, 0, 8, 0.0F);
        this.setRotateAngle(flapper, 0.7853981633974483F, 0.0F, 0.0F);
        this.consoleRight = new ModelRenderer(this, 0, 2);
        this.consoleRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.consoleRight.addBox(5.0F, -5.0F, -5.0F, 0, 5, 10, 0.0F);
        this.consoleTop = new ModelRenderer(this, 10, 22);
        this.consoleTop.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.consoleTop.addBox(-5.0F, -5.0F, -4.2F, 10, 0, 10, 0.0F);
        this.setRotateAngle(consoleTop, 0.17453292519943295F, 0.0F, 0.0F);
        this.consoleBottom = new ModelRenderer(this, -10, 22);
        this.consoleBottom.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.consoleBottom.addBox(-5.0F, -5.0F, -5.0F, 10, 0, 10, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.consoleBottom.render(f5);
        this.consoleFront.render(f5);
        this.consoleBehind.render(f5);
        this.pillar.render(f5);
        this.flapper.render(f5);
        this.consoleRight.render(f5);
        this.consoleLeft.render(f5);
        this.consoleTop.render(f5);
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
