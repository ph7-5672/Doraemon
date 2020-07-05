package org.ph7.doraemon.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ItemStackRenderer;
import org.ph7.doraemon.item.armor.ItemBambooDragonfly;
import org.ph7.doraemon.item.armor.ItemDimensionalPocket;
import org.ph7.doraemon.item.tool.ItemFishingRodAuto;
import org.ph7.doraemon.item.weapon.ItemAirGun;
import org.ph7.doraemon.item.weapon.ItemDictatorButton;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static List<Item> ITEMS = new ArrayList<>();
    public static ItemDimensionalPocket DIMENSIONAL_POCKET = new ItemDimensionalPocket();
    public static ItemBambooDragonfly BAMBOO_DRAGONFLY = new ItemBambooDragonfly();
    public static ItemFishingRodAuto FISHING_ROD_AUTO = new ItemFishingRodAuto();
    public static ItemAirGun AIR_GUN = new ItemAirGun();
    public static ItemDictatorButton DICTATOR_BUTTON = new ItemDictatorButton();

    public static void init()
    {
        register(DIMENSIONAL_POCKET, "dimensional_pocket");
        register(BAMBOO_DRAGONFLY, "bamboo_dragonfly");
        register(FISHING_ROD_AUTO, "fishing_rod_auto");
        register(DICTATOR_BUTTON, "dictator_button");
        //register(AIR_GUN, "air_gun");
    }

    private static Item register(Item item, String name)
    {
        item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        item.setUnlocalizedName(name);
        item.setCreativeTab(Reference.CREATIVE_TAB);
        item.setTileEntityItemStackRenderer(ItemStackRenderer.instance);

        NonNullList<ItemStack> list = NonNullList.create();
        item.getSubItems(item.getCreativeTab(), list);

        for (ItemStack stack : list)
        {
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, stack.getUnlocalizedName().replace("item.", ""));

            ModelLoader.setCustomModelResourceLocation(item, stack.getMetadata(), new ModelResourceLocation(location, "inventory"));
        }

        ForgeRegistries.ITEMS.register(item);

        ITEMS.add(item);

        return item;
    }
}
