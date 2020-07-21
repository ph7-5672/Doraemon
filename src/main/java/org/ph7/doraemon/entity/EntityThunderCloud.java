package org.ph7.doraemon.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.ph7.doraemon.init.ModItems;

public class EntityThunderCloud extends EntityBase
{
    public EntityThunderCloud(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected ItemStack getDropItem()
    {
        return new ItemStack(ModItems.THUNDER_CLOUD);
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
