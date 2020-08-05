package org.ph7.doraemon.render.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.living.EntityDoraemon;
import org.ph7.doraemon.model.ModelDoraemon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderDoraemon extends RenderLivingBase<EntityDoraemon>
{

    private static final ResourceLocation TEXTURE_DORAEMON = new ResourceLocation(Reference.MOD_ID, "textures/entity/living/doraemon.png");

    public RenderDoraemon(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDoraemon(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDoraemon entity)
    {
        return TEXTURE_DORAEMON;
    }
}
