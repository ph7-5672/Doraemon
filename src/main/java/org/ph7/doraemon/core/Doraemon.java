package org.ph7.doraemon.core;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
import org.ph7.doraemon.common.Reference;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION,
        acceptedMinecraftVersions = "[1.12.2]"
)
public class Doraemon
{
    @Mod.Instance("doraemon")
    public static Doraemon instance;

    public static Logger logger;

    @SidedProxy(clientSide = "org.ph7.doraemon.core.ClientProxy", serverSide = "org.ph7.doraemon.core.CommonProxy")
    public static CommonProxy proxy;

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public static void registerCmd(FMLServerStartingEvent event)
    {
        proxy.registerCmd(event);
    }

}
