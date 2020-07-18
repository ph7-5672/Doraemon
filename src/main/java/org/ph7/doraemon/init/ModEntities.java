package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.entity.EntitySunnyDoll;
import org.ph7.doraemon.entity.EntityWallpaper;
import org.ph7.doraemon.entity.EntityWeatherBox;
import org.ph7.doraemon.render.RenderRandomDoor;
import org.ph7.doraemon.render.RenderSunnyDoll;
import org.ph7.doraemon.render.RenderWallpaper;
import org.ph7.doraemon.render.RenderWeatherBox;

import java.util.List;
import java.util.Map;

public class ModEntities
{

    public static List<EntityEntry> ENTITY_ENTRIES;
    public static Map<Class, IRenderFactory> entityRenderMap = Maps.newHashMap();

    public static void init()
    {
        ENTITY_ENTRIES = Lists.newArrayList();
        //register("love_arrow", EntityLoveArrow.class);
        register( "random_door", EntityRandomDoor.class, RenderRandomDoor::new);
        register("wall_paper", EntityWallpaper.class, RenderWallpaper::new);
        register("sunny_doll", EntitySunnyDoll.class, RenderSunnyDoll::new);
        register("weather_box", EntityWeatherBox.class, RenderWeatherBox::new);
    }

    private static <E extends Entity> void register(String name, Class<E> entityClass)
    {
        register(name, entityClass, null);
    }

    private static <E extends Entity> void register(String name, Class<E> entityClass, IRenderFactory<? super E> factory)
    {
        EntityEntry entry = EntityEntryBuilder
                .create()
                .entity(entityClass)
                .id(new ResourceLocation(Reference.MOD_ID, name), ENTITY_ENTRIES.size())
                .name(name)
                .tracker(80, 3, false)
                .build();
        ENTITY_ENTRIES.add(entry);
        if (factory != null)
        {
            entityRenderMap.put(entityClass, factory);
        }
    }

}
