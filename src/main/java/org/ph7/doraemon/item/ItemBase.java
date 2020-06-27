package org.ph7.doraemon.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.ph7.doraemon.common.Reference;


public class ItemBase extends Item
{

    public ItemBase(String name)
    {
        this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        this.setUnlocalizedName(name);
    }

}
