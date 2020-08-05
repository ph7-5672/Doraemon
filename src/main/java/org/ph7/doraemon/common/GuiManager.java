package org.ph7.doraemon.common;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.gui.GuiDoraemon;
import org.ph7.doraemon.gui.GuiRandomDoor;
import java.util.List;

public class GuiManager
{
    public static List<Factory> factories = Lists.newArrayList();

    static
    {
        //0
        register(
                ((p,w,x,y,z) -> new GuiRandomDoor((EntityRandomDoor) w.getEntityByID(x))),
                ((p,w,x,y,z) -> null)
        );
        //1
        register(
                ((p,w,x,y,z) -> new GuiDoraemon(p)),
                ((p,w,x,y,z) -> null)
        );

    }

    public static void register(ClientFactory clientFactory, ServerFactory serverFactory)
    {
        factories.add(new Factory(clientFactory, serverFactory));
    }

    public static class Factory implements ClientFactory, ServerFactory
    {
        private ClientFactory clientFactory;
        private ServerFactory serverFactory;

        public Factory(ClientFactory clientFactory, ServerFactory serverFactory)
        {
            this.clientFactory = clientFactory;
            this.serverFactory = serverFactory;
        }

        @Override
        public Gui getClientElement(EntityPlayer player, World world, int x, int y, int z)
        {
            return clientFactory.getClientElement(player, world, x, y, z);
        }

        @Override
        public Container getServerElement(EntityPlayer player, World world, int x, int y, int z)
        {
            return serverFactory.getServerElement(player, world, x, y, z);
        }
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
        for (int i = 0; i < factories.size(); i++)
        {
            Factory factory = factories.get(i);
            Gui gui = factory.getClientElement(player, world, x, y, z);
            if (guiClass.equals(gui.getClass()))
            {
                player.openGui(Doraemon.instance, i, world, x, y, z);
                return;
            }
        }
    }

    public static void openGui(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        player.openGui(Doraemon.instance, id, world, x, y, z);
    }
}
