package org.ph7.doraemon.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityWallpaper;
import org.ph7.doraemon.model.ModelWallpaper;
import javax.annotation.Nullable;

public class RenderWallpaper extends Render<EntityWallpaper>
{
    private final ModelWallpaper modelWallpaper = new ModelWallpaper(10, 10);
    private static final ResourceLocation TEXTURE_WALLPAPER = new ResourceLocation(Reference.MOD_ID, "textures/entity/wallpaper.png");

    public RenderWallpaper(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    public void doRender(EntityWallpaper entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        //ModelBase model = entity.getModel();
        this.bindEntityTexture(entity);
        modelWallpaper.render(entity, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityWallpaper entity)
    {
        return TEXTURE_WALLPAPER;
    }
}
