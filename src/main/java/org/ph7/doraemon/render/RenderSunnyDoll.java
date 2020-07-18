package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
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
public class RenderSunnyDoll extends RenderBase<EntitySunnyDoll>
{

    private static final ResourceLocation TEXTURE_SUNNY_DOLL = new ResourceLocation(Reference.MOD_ID, "textures/entity/sunny_doll.png");
    private final ModelSunnyDoll modelSunnyDoll = new ModelSunnyDoll();

    public RenderSunnyDoll(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    protected ModelBase getModel()
    {
        return modelSunnyDoll;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySunnyDoll entity)
    {
        return TEXTURE_SUNNY_DOLL;
    }
}
