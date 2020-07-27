package org.ph7.doraemon.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.ph7.doraemon.common.ISubItem;


public abstract class ItemSubBase extends ItemBase implements ISubItem
{
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            int size = this.getSubNames().size();
            for (int i = 0; i < size; i++)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + this.getSubNames().get(stack.getMetadata());
    }
}
