package org.ph7.doraemon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityItemBase extends Entity
{
    public EntityItemBase(World worldIn)
    {
        super(worldIn);
    }


    //是否可以交互（鼠标左右键）
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    protected abstract Item getDropItem();

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
                this.dropItemWithOffset(this.getDropItem(), 1, 0.0F);
            }

            this.setDead();
        }

        return true;
    }
}
