package org.ph7.doraemon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.ph7.doraemon.init.ModItems;

import javax.annotation.Nullable;

public class EntitySunnyDoll extends Entity
{
    public EntitySunnyDoll(World worldIn)
    {
        super(worldIn);
    }


    @Override
    public void onUpdate()
    {
        if (!this.isDead)
        {
            //设置天气
            WorldInfo worldInfo = this.world.getWorldInfo();
            if (worldInfo.isRaining() || worldInfo.isThundering())
            {
                worldInfo.setCleanWeatherTime(1);
                worldInfo.setRainTime(0);
                worldInfo.setThunderTime(0);
                worldInfo.setRaining(false);
                worldInfo.setThundering(false);
            }
        }

        super.onUpdate();

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.world.isRemote)
        {
            if (this.world.getGameRules().getBoolean("doEntityDrops"))
            {
                this.dropItemWithOffset(ModItems.SUNNY_DOLL, 1, 0.0F);
            }

            this.setDead();
        }

        return true;
    }

    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
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
}
