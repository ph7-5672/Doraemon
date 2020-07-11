package org.ph7.doraemon.common;

import com.google.common.collect.Maps;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.gui.GuiRandomDoor;
import java.util.Iterator;
import java.util.Map;

public class GuiManager
{
    public static Map<ClientFactory, ServerFactory> GUI_MAP = Maps.newLinkedHashMap();

    static
    {
        GUI_MAP.put(
                ((p,w,x,y,z) -> new GuiRandomDoor((EntityRandomDoor) w.getEntityByID(x))),
                ((p,w,x,y,z) -> null)
        );
    }


    public interface ClientFactory
    {
        Gui getClientElement(EntityPlayer player, World world, int x, int y, int z);
    }

    public interface ServerFactory
    {
        Container getServerElement(EntityPlayer player, World world, int x, int y, int z);
    }

    public static void openGui(Class<? extends Gui> guiClass, EntityPlayer player, World world, int x, int y, int z)
    {
        Iterator<ClientFactory> iterator = GUI_MAP.keySet().iterator();
        for (int i = 0;iterator.hasNext(); i++)
        {
            ClientFactory factory = iterator.next();
            Gui element = factory.getClientElement(player, world, x, y, z);
            if (element.getClass().equals(guiClass))
            {
                player.openGui(Doraemon.instance, i, world, x, y, z);
            }
        }
    }

}
