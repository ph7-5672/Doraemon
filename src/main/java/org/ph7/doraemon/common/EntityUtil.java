package org.ph7.doraemon.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.Map;

public class EntityUtil
{
    public static Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap;

    static
    {
        entityRenderMap = Minecraft.getMinecraft().getRenderManager().entityRenderMap;
    }

    public static Render<? extends Entity> getRender(Class<? extends Entity> entityClass)
    {
        return entityRenderMap.get(entityClass);
    }

    public static void scale(EntityLivingBase entity, float widthScale, float heightScale)
    {
        IAttributeInstance width = entity.getEntityAttribute(Attributes.ENTITY_WIDTH_SCALE);
        IAttributeInstance height = entity.getEntityAttribute(Attributes.ENTITY_HEIGHT_SCALE);
        float f1 = (float) width.getBaseValue();
        float f2 = (float) height.getBaseValue();
        width.setBaseValue(widthScale);
        height.setBaseValue(heightScale);
        entity.width = entity.width * widthScale / f1;
        entity.height = entity.height * heightScale / f2;

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            player.eyeHeight = player.eyeHeight * heightScale / f2;
            if (player.isSneaking())
            {
                player.height *= 0.91666666666f;
                player.eyeHeight *= 0.9382716f;
            }
            if (player.isElytraFlying())
            {
                player.height *= 0.33f;
            }
            if (player.isPlayerSleeping())
            {
                player.width = 0.2F;
                player.height = 0.2F;
            }

        }

        double d0 = (double)entity.width / 2.0D;
        AxisAlignedBB aabb = entity.getEntityBoundingBox();
        entity.setEntityBoundingBox(new AxisAlignedBB(entity.posX - d0, aabb.minY, entity.posZ - d0, entity.posX + d0, aabb.minY + (double)entity.height, entity.posZ + d0));

    }

    public static float getWidthScale(EntityLivingBase entity)
    {
        IAttributeInstance width = entity.getEntityAttribute(Attributes.ENTITY_WIDTH_SCALE);
        return (float) width.getBaseValue();
    }

    public static float getHeightScale(EntityLivingBase entity)
    {
        IAttributeInstance height = entity.getEntityAttribute(Attributes.ENTITY_HEIGHT_SCALE);
        return (float) height.getBaseValue();
    }

    public static void setScale(EntityLivingBase entity, float widthScale, float heightScale)
    {
        entity.getEntityAttribute(Attributes.ENTITY_WIDTH_SCALE).setBaseValue(widthScale);
        entity.getEntityAttribute(Attributes.ENTITY_HEIGHT_SCALE).setBaseValue(heightScale);
    }

    public static float getDefaultWidth(EntityLivingBase entity)
    {
        return (float) entity.getEntityAttribute(Attributes.ENTITY_WIDTH).getBaseValue();
    }

    public static float getDefaultHeight(EntityLivingBase entity)
    {
        return (float) entity.getEntityAttribute(Attributes.ENTITY_HEIGHT).getBaseValue();
    }

    public static void setDefaultSize(EntityLivingBase entity, float width, float height)
    {
        entity.getEntityAttribute(Attributes.ENTITY_WIDTH).setBaseValue(width);
        entity.getEntityAttribute(Attributes.ENTITY_HEIGHT).setBaseValue(height);
    }

}
