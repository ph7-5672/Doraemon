package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ItemStackRenderer;
import org.ph7.doraemon.item.armor.ItemBambooDragonfly;
import org.ph7.doraemon.item.armor.ItemDimensionalPocket;
import org.ph7.doraemon.item.tool.ItemFishingRodAuto;
import org.ph7.doraemon.item.tool.ItemRandomDoor;
import org.ph7.doraemon.item.tool.ItemSunnyDoll;
import org.ph7.doraemon.item.tool.ItemWallpaperHouse;
import org.ph7.doraemon.item.tool.light.ItemLightShrink;
import org.ph7.doraemon.item.tool.light.ItemRecoverLight;
import org.ph7.doraemon.item.weapon.ItemAirGun;
import org.ph7.doraemon.item.weapon.ItemDictatorButton;
import org.ph7.doraemon.item.weapon.ItemLoveArrow;
import java.util.List;

public class ModItems
{
    public static List<Item> ITEMS = Lists.newArrayList();
    public static ItemDimensionalPocket DIMENSIONAL_POCKET = new ItemDimensionalPocket();
    public static ItemBambooDragonfly BAMBOO_DRAGONFLY = new ItemBambooDragonfly();
    public static ItemFishingRodAuto FISHING_ROD_AUTO = new ItemFishingRodAuto();
    public static ItemDictatorButton DICTATOR_BUTTON = new ItemDictatorButton();
    public static ItemLoveArrow LOVE_ARROW = new ItemLoveArrow();
    public static ItemRandomDoor RANDOM_DOOR = new ItemRandomDoor();
    public static ItemSunnyDoll SUNNY_DOLL = new ItemSunnyDoll();
    

    //todo 待完成
    public static ItemWallpaperHouse WALLPAPER_HOUSE = new ItemWallpaperHouse(10, 10);
    public static ItemRecoverLight RECOVER_LIGHT = new ItemRecoverLight();
    public static ItemAirGun AIR_GUN = new ItemAirGun();
    public static ItemLightShrink LIGHT_SHRINK = new ItemLightShrink();

    static
    {
        register(DIMENSIONAL_POCKET, "dimensional_pocket");
        register(BAMBOO_DRAGONFLY, "bamboo_dragonfly");
        register(FISHING_ROD_AUTO, "fishing_rod_auto");
        register(DICTATOR_BUTTON, "dictator_button");
        register(LOVE_ARROW, "love_arrow");
        register(RANDOM_DOOR, "random_door");
        register(SUNNY_DOLL, "sunny_doll");

    }

    private static void register(Item item, String name)
    {
        item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        item.setUnlocalizedName(name);
        item.setCreativeTab(Reference.CREATIVE_TAB);
        item.setTileEntityItemStackRenderer(ItemStackRenderer.instance);

        ITEMS.add(item);
    }
}
