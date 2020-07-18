package org.ph7.doraemon.entity;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.ph7.doraemon.init.ModItems;

public class EntityThunderCloud extends EntityItemBase
{
    public EntityThunderCloud(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected Item getDropItem()
    {
        return ModItems.THUNDER_CLOUD;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }


}
