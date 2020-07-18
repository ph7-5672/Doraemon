package org.ph7.doraemon.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityWeatherBox;

public class ClientProxy extends CommonProxy
{
    public static KeyBinding openDimensionalPocket = new KeyBinding(Reference.KEYS_INVENTORY, Keyboard.KEY_B, Reference.KEYS_CATEGORY);

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        registerKeyBindings();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        registerEntityRenderer();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(openDimensionalPocket);
    }

    public void registerEntityRenderer()
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.entityRenderer = new EntityWeatherBox.WeatherRenderer(mc, mc.getResourceManager());
    }
}
