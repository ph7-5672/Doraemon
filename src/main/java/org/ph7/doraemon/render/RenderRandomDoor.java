package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.model.ModelRandomDoor;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderRandomDoor extends RenderBase<EntityRandomDoor>
{

    private static final ResourceLocation TEXTURE_RANDOM_DOOR = new ResourceLocation(Reference.MOD_ID, "textures/entity/random_door.png");
    private ModelRandomDoor modelDoor = new ModelRandomDoor();

    public RenderRandomDoor(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    protected ModelBase getModel()
    {
        return modelDoor;
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRandomDoor entity)
    {
        return TEXTURE_RANDOM_DOOR;
    }

    @Override
    protected void translate(double x, double y, double z)
    {
        super.translate(x, y + 0.375D, z);
    }
}
