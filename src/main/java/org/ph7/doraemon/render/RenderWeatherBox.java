package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityWeatherBox;
import org.ph7.doraemon.model.ModelWeatherBox;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderWeatherBox extends RenderBase<EntityWeatherBox>
{
    private static final ResourceLocation TEXTURE_WEATHER_BOX = new ResourceLocation(Reference.MOD_ID, "textures/entity/weather_box.png");
    private final ModelWeatherBox modelWeatherBox = new ModelWeatherBox();

    public RenderWeatherBox(RenderManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ModelBase getModel()
    {
        return modelWeatherBox;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityWeatherBox entity)
    {
        return TEXTURE_WEATHER_BOX;
    }
}
