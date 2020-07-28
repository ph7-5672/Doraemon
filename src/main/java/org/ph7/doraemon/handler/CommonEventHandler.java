package org.ph7.doraemon.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.item.Item;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.ph7.doraemon.common.Attributes;
import org.ph7.doraemon.common.EntityUtil;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModEntities;
import org.ph7.doraemon.init.ModItems;


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
    public void attachAttributes(EntityEvent.EntityConstructing event)
    {
        if (event.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase)event.getEntity();
            AbstractAttributeMap map = entity.getAttributeMap();
            map.registerAttribute(Attributes.ENTITY_WIDTH_SCALE);
            map.registerAttribute(Attributes.ENTITY_HEIGHT_SCALE);
            map.registerAttribute(Attributes.ENTITY_WIDTH);
            map.registerAttribute(Attributes.ENTITY_HEIGHT);
        }
    }

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        float widthScale = EntityUtil.getWidthScale(entity);
        float heightScale = EntityUtil.getHeightScale(entity);

        float defaultWidth = EntityUtil.getDefaultWidth(entity);
        float defaultHeight = EntityUtil.getDefaultHeight(entity);

        if (widthScale != 1.0F || heightScale != 1.0F)
        {
            if (defaultWidth == entity.width || defaultHeight == entity.height)
            {
                EntityUtil.setScale(entity, 1.0F, 1.0F);
                EntityUtil.scale(entity, widthScale, heightScale);
            }
        }
    }

    @SubscribeEvent
    public void entityCreate(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            EntityUtil.setDefaultSize(entity, entity.width, entity.height);
        }

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
