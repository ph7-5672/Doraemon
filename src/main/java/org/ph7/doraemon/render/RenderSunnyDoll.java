package org.ph7.doraemon.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntitySunnyDoll;
import org.ph7.doraemon.model.ModelSunnyDoll;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderSunnyDoll extends Render<EntitySunnyDoll>
{

    private static final ResourceLocation TEXTURE_SUNNY_DOLL = new ResourceLocation(Reference.MOD_ID, "textures/entity/sunny_doll.png");
    private final ModelSunnyDoll modelSunnyDoll = new ModelSunnyDoll();

    public RenderSunnyDoll(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    public void doRender(EntitySunnyDoll entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(90.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));

        }

        modelSunnyDoll.render(entity, 0, 0,-0.1F,0,0,0.0625F);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySunnyDoll entity)
    {
        return TEXTURE_SUNNY_DOLL;
    }
}
