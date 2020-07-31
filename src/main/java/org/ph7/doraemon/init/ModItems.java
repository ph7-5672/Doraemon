package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ItemStackRenderer;
import org.ph7.doraemon.item.armor.ItemBambooDragonfly;
import org.ph7.doraemon.item.armor.ItemDimensionalPocket;
import org.ph7.doraemon.item.food.ItemMemoryBread;
import org.ph7.doraemon.item.light.ItemLight;
import org.ph7.doraemon.item.misc.ItemPencilAuto;
import org.ph7.doraemon.item.misc.ItemScarecrow;
import org.ph7.doraemon.item.misc.ItemVendingMachine;
import org.ph7.doraemon.item.portal.ItemRandomDoor;
import org.ph7.doraemon.item.room.ItemWallpaperHouse;
import org.ph7.doraemon.item.tool.ItemDigGlove;
import org.ph7.doraemon.item.tool.ItemFishingRodAuto;
import org.ph7.doraemon.item.weapon.ItemAirGun;
import org.ph7.doraemon.item.weapon.ItemDictatorButton;
import org.ph7.doraemon.item.misc.ItemLoveArrow;
import org.ph7.doraemon.item.weather.ItemSunnyDoll;
import org.ph7.doraemon.item.weather.ItemThunderCloud;
import org.ph7.doraemon.item.weather.ItemWeatherBox;

import java.util.List;

public class ModItems
{
    public static List<Item> ITEMS = Lists.newArrayList();
    //装备类
    public static ItemDimensionalPocket DIMENSIONAL_POCKET = new ItemDimensionalPocket();
    public static ItemBambooDragonfly BAMBOO_DRAGONFLY = new ItemBambooDragonfly();

    //工具类
    public static ItemFishingRodAuto FISHING_ROD_AUTO = new ItemFishingRodAuto();
    public static ItemDigGlove DIG_GLOVE = new ItemDigGlove();

    //武器类
    public static ItemDictatorButton DICTATOR_BUTTON = new ItemDictatorButton();
    public static ItemAirGun AIR_GUN = new ItemAirGun();

    //传送门类
    public static ItemRandomDoor RANDOM_DOOR = new ItemRandomDoor();

    //天气
    public static ItemSunnyDoll SUNNY_DOLL = new ItemSunnyDoll();
    public static ItemWeatherBox WEATHER_BOX = new ItemWeatherBox();

    //杂项
    public static ItemLoveArrow LOVE_ARROW = new ItemLoveArrow();
    public static ItemScarecrow SCARECROW = new ItemScarecrow();
    public static ItemVendingMachine VENDING_MACHINE = new ItemVendingMachine();
    public static ItemPencilAuto PENCIL_AUTO = new ItemPencilAuto();

    //光线
    public static ItemLight LIGHT = new ItemLight();

    //食物
    public static ItemMemoryBread MEMORY_BREAD = new ItemMemoryBread();


    //todo 待完成
    public static ItemThunderCloud THUNDER_CLOUD = new ItemThunderCloud();
    public static ItemWallpaperHouse WALLPAPER_HOUSE = new ItemWallpaperHouse(10, 10);

    static
    {
        register(DIMENSIONAL_POCKET, "dimensional_pocket");
        register(BAMBOO_DRAGONFLY, "bamboo_dragonfly");
        register(FISHING_ROD_AUTO, "fishing_rod_auto");
        register(DICTATOR_BUTTON, "dictator_button");
        register(LOVE_ARROW, "love_arrow");
        register(RANDOM_DOOR, "random_door");
        register(SUNNY_DOLL, "sunny_doll");
        register(WEATHER_BOX, "weather_box");
        register(SCARECROW, "scarecrow");
        register(VENDING_MACHINE, "vending_machine");
        register(AIR_GUN, "air_gun");
        register(LIGHT, "light");
        //register(DIG_GLOVE, "dig_glove");
        register(MEMORY_BREAD, "memory_bread");
        register(PENCIL_AUTO, "pencil_auto");
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
