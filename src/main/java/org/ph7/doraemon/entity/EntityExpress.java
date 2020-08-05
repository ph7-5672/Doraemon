package org.ph7.doraemon.entity;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

@Deprecated
public class EntityExpress extends EntityBase
{

    private static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(EntityExpress.class, DataSerializers.ITEM_STACK);

    public EntityExpress(World worldIn)
    {
        super(worldIn);
    }

    public void setItem(ItemStack item)
    {
        this.dataManager.set(ITEM, item);
    }

    public ItemStack getItem()
    {
        return this.dataManager.get(ITEM);
    }

    @Override
    protected ItemStack getDropItem()
    {
        return this.getItem();
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(ITEM, ItemStack.EMPTY);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("DoraItem"))
        {
            NonNullList<ItemStack> list = NonNullList.create();
            ItemStackHelper.loadAllItems(compound, list);
            if (!list.isEmpty()) this.setItem(list.get(0));
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        ItemStack item = this.getItem();
        NonNullList<ItemStack> list = NonNullList.create();
        list.add(item);
        ItemStackHelper.saveAllItems(compound, list);
    }
}
