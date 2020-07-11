package org.ph7.doraemon.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import org.ph7.doraemon.init.ModItems;

public class EntityLoveArrow extends EntityTippedArrow
{
    public EntityLoveArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
        this.setDamage(0);
    }

    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(ModItems.LOVE_ARROW);
    }

    @Override
    protected void arrowHit(EntityLivingBase living)
    {
        double d0 = this.rand.nextGaussian() * 0.02D;
        double d1 = this.rand.nextGaussian() * 0.02D;
        double d2 = this.rand.nextGaussian() * 0.02D;
        living.world.spawnParticle(EnumParticleTypes.HEART, living.posX + (double)(this.rand.nextFloat() * living.width * 2.0F) - (double)living.width, living.posY + 0.5D + (double)(this.rand.nextFloat() * living.height), living.posZ + (double)(this.rand.nextFloat() * living.width * 2.0F) - (double)living.width, d0, d1, d2);
        if (living instanceof EntityAnimal)
        {
            ((EntityAnimal) living).setInLove(null);
        }
    }
}
