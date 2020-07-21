package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityScarecrow;
import org.ph7.doraemon.model.ModelScarecrow;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderScarecrow extends RenderBase<EntityScarecrow>
{

    private static final ResourceLocation TEXTURE_SCARECROW = new ResourceLocation(Reference.MOD_ID, "textures/entity/scarecrow.png");
    private final ModelScarecrow modelScarecrow = new ModelScarecrow();

    public RenderScarecrow(RenderManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ModelBase getModel()
    {
        return modelScarecrow;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityScarecrow entity)
    {
        return TEXTURE_SCARECROW;
    }

}
