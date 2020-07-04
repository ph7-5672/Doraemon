package org.ph7.doraemon.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.ph7.doraemon.common.Reference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class InventoryDimensionalPocket extends WorldSavedData implements IInventory
{
    protected NonNullList<ItemStack> inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    public static final String DATA_NAME = Reference.MOD_ID + ":dimensional_pocket";


    public InventoryDimensionalPocket()
    {
        super(DATA_NAME);
        this.markDirty();
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.size();
    }

    @Override
    public boolean isEmpty()
    {
        for(ItemStack itemstack : this.inventory)
        {
            if(!itemstack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return index >= 0 && index < this.inventory.size() ? this.inventory.get(index) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);

        if(!itemstack.isEmpty())
        {
            this.markDirty();
        }
        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack itemstack = this.inventory.get(index);

        if(itemstack.isEmpty())
        {
            return ItemStack.EMPTY;
        }
        else
        {
            this.inventory.set(index, ItemStack.EMPTY);
            return itemstack;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory.set(index, stack);

        if(!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }


    @Override
    public void markDirty()
    {
        super.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        this.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {

    }

    @Override
    public String getName()
    {
        return "InventoryDimensionalPocket";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return (this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        ItemStackHelper.loadAllItems(nbt, inventory);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        ItemStackHelper.saveAllItems(nbt, inventory);
        return nbt;
    }

    public static InventoryDimensionalPocket getInstance(World world)
    {
        MapStorage storage = world.getPerWorldStorage();
        InventoryDimensionalPocket instance = (InventoryDimensionalPocket) storage.getOrLoadData(InventoryDimensionalPocket.class, DATA_NAME);
        if (instance == null)
        {
            instance = new InventoryDimensionalPocket();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }


}
