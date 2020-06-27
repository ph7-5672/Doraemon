package org.ph7.doraemon.common;

import net.minecraft.inventory.Container;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

public class ItemUtil
{
    public static boolean itemEquals(ItemStack stack1, ItemStack stack2)
    {
        if (stack1.getItem() == stack2.getItem())
        {
            if(!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata())
            {
                return bookEnchantmentEquals(stack1, stack2) && itemEnchantmentEquals(stack1, stack2);
            }
        }
        return false;
    }

    public static boolean itemEnchantmentEquals(ItemStack stack1, ItemStack stack2)
    {
        NBTTagList tagList1 = stack1.getEnchantmentTagList();
        NBTTagList tagList2 = stack2.getEnchantmentTagList();
        return tagListEquals(tagList1, tagList2);
    }


    public static boolean bookEnchantmentEquals(ItemStack stack1, ItemStack stack2)
    {
        NBTTagList tagList1 = ItemEnchantedBook.getEnchantments(stack1);
        NBTTagList tagList2 = ItemEnchantedBook.getEnchantments(stack2);
        return tagListEquals(tagList1, tagList2);
    }


    private static boolean tagListEquals(NBTTagList tagList1, NBTTagList tagList2)
    {
        if (tagList1.tagCount() != tagList2.tagCount()) return false;
        i: for (int i = 0; i < tagList1.tagCount(); i++)
        {
            NBTTagCompound compound1 = tagList1.getCompoundTagAt(i);
            for (int j = 0; j < tagList2.tagCount(); j++)
            {
                NBTTagCompound compound2 = tagList2.getCompoundTagAt(j);
                if (compound1.getShort("id") == compound2.getShort("id") && compound1.getShort("lvl") == compound2.getShort("lvl"))
                {
                    continue i;
                }
            }
            return false;
        }
        return true;
    }

    public static int getSlotFor(Container container, ItemStack stack) {
        return getSlotFor(container, stack, 0);
    }

    public static int getSlotFor(Container container, ItemStack stack, int start)
    {
        return getSlotFor(container, stack, start, -1);
    }

    public static int getSlotFor(Container container, ItemStack stack, int start, int end)
    {
        NonNullList<ItemStack> inventory = container.getInventory();
        if (end > inventory.size() || end < 0) end = inventory.size();

        for (int i = start; i < end; i++)
        {
            ItemStack itemStack = inventory.get(i);
            if (itemEquals(stack, itemStack) && itemStack.getMaxStackSize() > itemStack.getCount())
            {
                return i;
            }
        }

        return -1;
    }

    public static int getBestHotbarSlot(Container container)
    {
        return getBestHotbarSlot(container, ItemStack.EMPTY);
    }

    public static int getBestHotbarSlot(Container container, ItemStack stack)
    {
        return getBestHotbarSlot(container, stack, 0);
    }

    public static int getBestHotbarSlot(Container container, ItemStack stack, int start)
    {
        return getBestHotbarSlot(container, stack, start, -1);
    }

    public static int getBestHotbarSlot(Container container, ItemStack stack, int start, int end)
    {
        int i = getSlotFor(container, stack, start, end);
        if (i < 0 && !stack.isEmpty()) return getBestHotbarSlot(container, ItemStack.EMPTY, start, end);
        return i;
    }

}
