package org.ph7.doraemon.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import java.lang.reflect.Field;
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

    public static ModelBase getLivingModel(Class<? extends EntityLivingBase> entityClass)
    {
        Render<? extends Entity> render = getRender(entityClass);
        Field[] fields = RenderLivingBase.class.getDeclaredFields();
        try
        {
            for (Field field : fields)
            {
                if (ModelBase.class.equals(field.getType()))
                {
                    return (ModelBase) field.get(render);
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

}
