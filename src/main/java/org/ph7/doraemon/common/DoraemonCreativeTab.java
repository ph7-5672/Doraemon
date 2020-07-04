package org.ph7.doraemon.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.ph7.doraemon.init.ModItems;

public class DoraemonCreativeTab extends CreativeTabs
{
    public DoraemonCreativeTab()
    {
        super(Reference.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.DIMENSIONAL_POCKET);
    }
}
