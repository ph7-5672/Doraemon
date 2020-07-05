package org.ph7.doraemon.handler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.ph7.doraemon.common.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonEventHandler
{

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
    }

}
