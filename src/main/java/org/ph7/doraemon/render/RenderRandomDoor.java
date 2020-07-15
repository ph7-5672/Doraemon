package org.ph7.doraemon.render;

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
public class RenderRandomDoor extends Render<EntityRandomDoor>
{

    private static final ResourceLocation TEXTURE_RANDOM_DOOR = new ResourceLocation(Reference.MOD_ID, "textures/entity/random_door.png");
    private ModelRandomDoor modelDoor = new ModelRandomDoor();

    public RenderRandomDoor(RenderManager renderManager)
    {
        super(renderManager);
    }


    @Override
    public void doRender(EntityRandomDoor entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 0.375F, (float)z);
        this.setupRotation(entityYaw);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        modelDoor.render(entity, partialTicks, 0, -0.1F, 0, 0, 0.0625F);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(float yaw)
    {
        GlStateManager.rotate(90.0F - yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRandomDoor entity)
    {
        return TEXTURE_RANDOM_DOOR;
    }

}
