package org.ph7.doraemon.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.ph7.doraemon.common.EnumWeather;
import org.ph7.doraemon.init.ModItems;
import javax.annotation.Nullable;
import java.util.List;

//todo 待优化（与天气模组兼容）
public class EntityWeatherBox extends EntityItemBase
{
    protected static DataParameter<Integer> WEATHER = EntityDataManager.createKey(EntityWeatherBox.class, DataSerializers.VARINT);

    public EntityWeatherBox(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.5F);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {

        Minecraft mc = Minecraft.getMinecraft();

        EnumWeather weather = this.getWeather();
        int meta = weather.getMeta();
        if (++meta >= EnumWeather.values().length) meta = 0;
        EnumWeather newWeather = EnumWeather.getByMeta(meta);
        this.setWeather(newWeather);
        mc.ingameGUI.setOverlayMessage(I18n.format("weather_box.info") + ": " + this.getWeatherInfo(), false);

        List<EntitySunnyDoll> entities = this.world.getEntities(EntitySunnyDoll.class, EntitySelectors.IS_ALIVE);
        if (!entities.isEmpty())
        {
            return false;
        }

        int i = 12380 * 20;
        WorldInfo worldInfo = world.getWorldInfo();

        if (newWeather == EnumWeather.RAIN || newWeather == EnumWeather.RAIN_SNOW)
        {
            worldInfo.setCleanWeatherTime(0);
            worldInfo.setRainTime(i);
            worldInfo.setRaining(true);
            worldInfo.setThunderTime(0);
            worldInfo.setThundering(false);
        }
        else if (newWeather == EnumWeather.THUNDER)
        {
            worldInfo.setCleanWeatherTime(0);
            worldInfo.setRainTime(i);
            worldInfo.setRaining(true);
            worldInfo.setThunderTime(i);
            worldInfo.setThundering(true);
        }
        else
        {
            worldInfo.setCleanWeatherTime(i);
            worldInfo.setRainTime(0);
            worldInfo.setThunderTime(0);
            worldInfo.setRaining(false);
            worldInfo.setThundering(false);
        }

        return true;
    }

    @Override
    protected Item getDropItem()
    {
        return ModItems.WEATHER_BOX;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(WEATHER, 0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("Weather"))
        {
            int meta = compound.getInteger("Weather");
            EnumWeather weather = EnumWeather.getByMeta(meta);
            this.setWeather(weather);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setInteger("Weather", this.getWeather().getMeta());
    }

    public EnumWeather getWeather()
    {
        Integer meta = this.dataManager.get(WEATHER);
        return EnumWeather.getByMeta(meta);
    }

    public void setWeather(EnumWeather weather)
    {
        this.dataManager.set(WEATHER, weather.getMeta());
    }

    public String getWeatherInfo()
    {
        return I18n.format(this.getWeather().getKey());
    }


}
