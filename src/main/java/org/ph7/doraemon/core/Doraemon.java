package org.ph7.doraemon.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.init.ModItems;

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

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        ModItems.init();
    }

}
