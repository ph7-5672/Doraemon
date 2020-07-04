package org.ph7.doraemon.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ClientEventHandler;
import org.ph7.doraemon.handler.GuiHandler;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.network.GuiPacket;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ModItems.init();
        registerEventHandler();
        registerGuiHandler();
        registerPackets();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public void registerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    public void registerGuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Doraemon.instance, new GuiHandler());
    }

    public void registerPackets()
    {
        Doraemon.NETWORK.registerMessage(GuiPacket.Handler.class, GuiPacket.class, 0, Side.SERVER);
    }
}
