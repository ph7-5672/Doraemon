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
        PRICES.put(new ItemStack(ModItems.DIMENSIONAL_POCKET), emerald(1));
        PRICES.put(new ItemStack(ModItems.BAMBOO_DRAGONFLY), emerald(10));

        PRICES.put(new ItemStack(ModItems.FISHING_ROD_AUTO), emerald(1));

        PRICES.put(new ItemStack(ModItems.DICTATOR_BUTTON), emerald(5));
        PRICES.put(new ItemStack(ModItems.AIR_GUN), emerald(2));

        PRICES.put(new ItemStack(ModItems.RANDOM_DOOR), emerald(20));

        PRICES.put(new ItemStack(ModItems.SUNNY_DOLL), emerald(1));
        PRICES.put(new ItemStack(ModItems.WEATHER_BOX), emerald(2));

        PRICES.put(new ItemStack(ModItems.LOVE_ARROW, 64, 0), emerald(1));
        PRICES.put(new ItemStack(ModItems.SCARECROW), emerald(2));
        
        PRICES.put(new ItemStack(ModItems.LIGHT, 1, 0), emerald(2));
        PRICES.put(new ItemStack(ModItems.LIGHT, 1, 1), emerald(2));
    }
    
    public static ItemStack emerald(int amount)
    {
        return new ItemStack(Items.EMERALD, amount, 0);
    }

    public static ItemStack gold(int amount)
    {
        return new ItemStack(Items.GOLD_INGOT, amount, 0);
    }
    
}
