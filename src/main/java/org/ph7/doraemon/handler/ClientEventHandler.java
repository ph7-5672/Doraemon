package org.ph7.doraemon.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.entity.EntityWeatherBox;
import org.ph7.doraemon.init.ModEntities;
import org.ph7.doraemon.init.ModItems;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class ClientEventHandler
{

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        ModItems.ITEMS.forEach(i ->
        {
            Doraemon.proxy.setModelResource(i);
        });

        ModEntities.entityRenderMap.forEach((x, y) ->
        {
            RenderingRegistry.registerEntityRenderingHandler(x, y);
        });
        /*ModBlocks.BLOCKS.forEach(b ->
        {
            if (b instanceof BlockBase)
            {
                if (((BlockBase) b).hasCustomModel())
                {
                    Doraemon.proxy.setModelResource(b);
                }
            }
        });*/

    }


}
