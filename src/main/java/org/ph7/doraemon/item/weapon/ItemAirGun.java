package org.ph7.doraemon.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.item.ItemBase;


public class ItemAirGun extends ItemBase
{

    public ItemAirGun()
    {
        this.setMaxStackSize(1);

    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    public static class EntityAirBullet extends EntityThrowable
    {
        public EntityAirBullet(World worldIn)
        {
            super(worldIn);
        }

        public EntityAirBullet(World worldIn, EntityLivingBase throwerIn)
        {
            super(worldIn, throwerIn);
        }

        @Override
        protected void onImpact(RayTraceResult result)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 8.0F);
            }

            if (!this.world.isRemote)
            {
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }
        }

    }

}
