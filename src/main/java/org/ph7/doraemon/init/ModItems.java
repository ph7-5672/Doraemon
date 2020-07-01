package org.ph7.doraemon.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.item.armor.ItemBambooDragonfly;
import org.ph7.doraemon.item.tool.ItemFishingRodAuto;

public class ModItems
{

    public static ItemBambooDragonfly BAMBOO_DRAGONFLY = new ItemBambooDragonfly();
    public static ItemFishingRodAuto FISHING_ROD_AUTO = new ItemFishingRodAuto();

    public static void init()
    {
        register(BAMBOO_DRAGONFLY);
        register(FISHING_ROD_AUTO);
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
