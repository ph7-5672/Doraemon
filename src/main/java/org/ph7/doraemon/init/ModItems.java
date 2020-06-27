package org.ph7.doraemon.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.item.ItemBambooDragonfly;

public class ModItems
{

    public static ItemBambooDragonfly BAMBOO_DRAGONFLY;

    public static void init()
    {
        BAMBOO_DRAGONFLY = (ItemBambooDragonfly)register(new ItemBambooDragonfly());
    }

    private static Item register(Item item)
    {
        NonNullList<ItemStack> list = NonNullList.create();
        item.getSubItems(item.getCreativeTab(), list);

        for (ItemStack stack : list)
        {
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, stack.getUnlocalizedName().replace("item.", ""));

            ModelLoader.setCustomModelResourceLocation(item, stack.getMetadata(), new ModelResourceLocation(location, "inventory"));
        }

        ForgeRegistries.ITEMS.register(item);

        return item;
    }
}
