package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityRandomDoor;
import java.util.List;

public class ModEntities
{

    public static List<EntityEntry> ENTITY_ENTRIES;

    public static void init()
    {
        ENTITY_ENTRIES = Lists.newArrayList();
        //register("love_arrow", EntityLoveArrow.class);
        register( "random_door", EntityRandomDoor.class);
    }

    private static <E extends Entity> void register(String name, Class<E> entityClass)
    {
        EntityEntry entry = EntityEntryBuilder
                .create()
                .entity(entityClass)
                .id(new ResourceLocation(Reference.MOD_ID, name), 233)
                .name(name)
                .tracker(80, 3, false)
                .build();
        ENTITY_ENTRIES.add(entry);
    }

}
