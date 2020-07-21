package org.ph7.doraemon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityBase extends Entity
{
    public EntityBase(World worldIn)
    {
        super(worldIn);
    }


    //是否可以交互（鼠标左右键）
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    protected abstract ItemStack getDropItem();

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
                if (!source.isCreativePlayer())
                {
                    this.entityDropItem(this.getDropItem(), 0.0F);
                }
            }

            this.setDead();
        }

        return true;
    }
}
