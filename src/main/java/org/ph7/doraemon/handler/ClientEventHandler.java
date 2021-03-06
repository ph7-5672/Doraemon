package org.ph7.doraemon.handler;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.ph7.doraemon.common.EntityUtil;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModEntities;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.item.weapon.ItemAirGun;

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
        ModBlocks.BLOCKS.forEach(b ->
        {
            Doraemon.proxy.setModelResource(b);
        });

    }

    @SubscribeEvent
    public void livingRenderPre(RenderLivingEvent.Pre event)
    {
        EntityLivingBase entity = event.getEntity();
        float widthScale = EntityUtil.getWidthScale(entity);
        float heightScale = EntityUtil.getHeightScale(entity);
        if (widthScale != 1.0F || heightScale != 1.0F)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(widthScale, heightScale, widthScale);
            GlStateManager.translate(event.getX() / widthScale - event.getX(),
                    event.getY() / heightScale - event.getY(), event.getZ() / widthScale - event.getZ());
        }

    }

    @SubscribeEvent
    public void livingRenderPost(RenderLivingEvent.Post event)
    {
        final EntityLivingBase entity = event.getEntity();

        float widthScale = EntityUtil.getWidthScale(entity);
        float heightScale = EntityUtil.getHeightScale(entity);
        if (widthScale != 1.0F || heightScale != 1.0F)
        {
            GlStateManager.popMatrix();
        }
    }

    @SubscribeEvent
    public void playerRenderPre(RenderLivingEvent.Pre event)
    {
        /*EntityLivingBase entity = event.getEntity();
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            //空气枪开火粒子
            ItemStack stack = player.getHeldItem(player.getActiveHand());
            Item item = stack.getItem();
            if (item instanceof ItemAirGun)
            {
                ItemAirGun airGun = (ItemAirGun) item;

            }
        }*/
    }

}
