package org.ph7.doraemon.gui.inventory;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.init.ModItems;

import java.util.ArrayList;
import java.util.List;


public class InventoryDimensionalPocket extends InventoryBase
{
    protected ItemStack storage;

    public InventoryDimensionalPocket(ItemStack storage, int size)
    {
        super(size);
        this.storage = storage;
        this.loadAll(storage.getTagCompound());
    }

    protected void saveAll(NBTTagCompound tagCompound)
    {
        if (tagCompound == null) tagCompound = new NBTTagCompound();
        tagCompound = ItemStackHelper.saveAllItems(tagCompound, inventory);
        this.storage.setTagCompound(tagCompound);
    }

    protected void loadAll(NBTTagCompound tagCompound)
    {
        if (tagCompound == null)
        {
            tagCompound = new NBTTagCompound();
            ArrayList<Item> list = Lists.newArrayList();
            list.add(ModItems.VENDING_MACHINE);
            ItemUtil.saveAllItems(tagCompound, list);
        }
        ItemStackHelper.loadAllItems(tagCompound, inventory);
    }

    @Override
    public void markDirty()
    {
        this.saveAll(this.storage.getTagCompound());
    }

    @Override
    public String getName()
    {
        return "item.dimensional_pocket.name";
    }



}
