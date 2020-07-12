package org.ph7.doraemon.common;


import net.minecraft.util.ResourceLocation;

public class Reference
{
    //===================Mod相关======================//
    public static final String MOD_ID = "doraemon";
    public static final String MOD_NAME = "Doraemon";
    public static final String MOD_VERSION = "1.1.0";
    public static final DoraemonCreativeTab CREATIVE_TAB = new DoraemonCreativeTab();

    public static final String KEYS_INVENTORY = "keys.doraemon.inventory";
    public static final String KEYS_CATEGORY = "keys.doraemon.category";

    //=================ResourceLocation==============//
    public static final ResourceLocation BLOCK_POS_CAP = new ResourceLocation(Reference.MOD_ID, "block_pos");

}
