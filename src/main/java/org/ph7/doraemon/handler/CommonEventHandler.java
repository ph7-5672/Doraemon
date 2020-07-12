package org.ph7.doraemon.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModEntities;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.render.RenderRandomDoor;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonEventHandler
{
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        ModItems.ITEMS.forEach(i ->
        {
            event.getRegistry().register(i);
        });

        ModBlocks.ITEM_BLOCKS.forEach(i ->
        {
            event.getRegistry().register(i);
        });
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        ModBlocks.BLOCKS.forEach(b ->
        {
            event.getRegistry().register(b);
        });
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        ModEntities.init();
        ModEntities.ENTITY_ENTRIES.forEach(e ->
        {
            event.getRegistry().register(e);
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        ModItems.ITEMS.forEach(i ->
        {
            Doraemon.proxy.setModelResource(i);
        });
        ModBlocks.BLOCKS.forEach(b ->
        {
            Doraemon.proxy.setModelResource(b);
        });
        ModBlocks.ITEM_BLOCKS.forEach(i ->
        {
            Doraemon.proxy.setModelResource(i);
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRandomDoor.class, manager -> new RenderRandomDoor(manager));
    }


    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
    }


    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
    }
}
