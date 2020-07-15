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
        this.setSize(0.2F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        if (!this.isDead)
        {
            //设置天气
            WorldInfo worldInfo = this.world.getWorldInfo();
            worldInfo.setCleanWeatherTime(1);
            worldInfo.setRainTime(0);
            worldInfo.setThunderTime(0);
            worldInfo.setRaining(false);
            worldInfo.setThundering(false);
        }

        super.onUpdate();

    }

    public boolean canBeCollidedWith()
    {
        return !this.isDead;
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
        return true;
    }
}
