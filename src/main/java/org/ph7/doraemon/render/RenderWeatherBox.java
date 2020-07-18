package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.EnumWeather;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.common.WeatherUtil;
import org.ph7.doraemon.entity.EntityWeatherBox;
import org.ph7.doraemon.model.ModelWeatherBox;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderWeatherBox extends RenderBase<EntityWeatherBox>
{
    private static final ResourceLocation TEXTURE_WEATHER_BOX = new ResourceLocation(Reference.MOD_ID, "textures/entity/weather_box.png");
    private final ModelWeatherBox modelWeatherBox = new ModelWeatherBox();
    private int updateCount;

    public RenderWeatherBox(RenderManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    public void doRender(EntityWeatherBox entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        if (entity.getWeather() == EnumWeather.SNOW || entity.getWeather() == EnumWeather.RAIN_SNOW)
        {
            WeatherUtil.snow(partialTicks, updateCount++);
        }
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

    @Override
    public boolean shouldRender(EntityWeatherBox livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
        return super.shouldRender(livingEntity, camera, camX, camY, camZ) || livingEntity.getWeather() == EnumWeather.SNOW || livingEntity.getWeather() == EnumWeather.RAIN_SNOW;
    }
}
