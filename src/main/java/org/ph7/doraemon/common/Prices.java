package org.ph7.doraemon.common;

import com.google.common.collect.Maps;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.ph7.doraemon.init.ModItems;

import java.util.Map;

public class Prices
{
    public static final Map<ItemStack, ItemStack> PRICES = Maps.newLinkedHashMap();

    static
    {
        PRICES.put(new ItemStack(ModItems.DIMENSIONAL_POCKET), new ItemStack(Items.EMERALD, 1, 0));
        PRICES.put(new ItemStack(ModItems.BAMBOO_DRAGONFLY), new ItemStack(Items.EMERALD, 10, 0));

        PRICES.put(new ItemStack(ModItems.FISHING_ROD_AUTO), new ItemStack(Items.EMERALD,1, 0));

        PRICES.put(new ItemStack(ModItems.DICTATOR_BUTTON), new ItemStack(Items.EMERALD,5, 0));
        PRICES.put(new ItemStack(ModItems.AIR_GUN), new ItemStack(Items.EMERALD,2, 0));

        PRICES.put(new ItemStack(ModItems.RANDOM_DOOR), new ItemStack(Items.EMERALD,20, 0));

        PRICES.put(new ItemStack(ModItems.SUNNY_DOLL), new ItemStack(Items.EMERALD,1, 0));
        PRICES.put(new ItemStack(ModItems.WEATHER_BOX), new ItemStack(Items.EMERALD,2, 0));

        PRICES.put(new ItemStack(ModItems.LOVE_ARROW, 64, 0), new ItemStack(Items.EMERALD,1, 0));
        PRICES.put(new ItemStack(ModItems.SCARECROW), new ItemStack(Items.EMERALD,2, 0));
    }
}
