package org.ph7.doraemon.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.ph7.doraemon.init.ModItems;

import javax.annotation.Nullable;

public class EntitySunnyDoll extends EntityItemBase
{
    public EntitySunnyDoll(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return ModItems.SUNNY_DOLL;
    }

    @Override
    public void onUpdate()
    {
        if (!this.isDead)
        {
            //设置天气
            WorldInfo worldInfo = this.world.getWorldInfo();
            worldInfo.setCleanWeatherTime(12380 * 20);
            worldInfo.setRainTime(0);
            worldInfo.setThunderTime(0);
            worldInfo.setRaining(false);
            worldInfo.setThundering(false);
        }

        super.onUpdate();

    }

    @Override
    public AxisAlignedBB getEntityBoundingBox()
    {
        return super.getEntityBoundingBox().offset(0, -1.1D, 0);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return this.getEntityBoundingBox();
    }


    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }

    protected boolean canTriggerWalking()
    {
        return false;
    }
}
