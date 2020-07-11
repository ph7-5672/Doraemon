package org.ph7.doraemon.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.ph7.doraemon.common.GuiManager;
import java.util.Iterator;
import java.util.Map;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		Iterator<Map.Entry<GuiManager.ClientFactory, GuiManager.ServerFactory>> iterator = GuiManager.GUI_MAP.entrySet().iterator();
		for (int i = 0; iterator.hasNext(); i++)
		{
			if (ID == i)
			{
				Map.Entry<GuiManager.ClientFactory, GuiManager.ServerFactory> entry = iterator.next();
				GuiManager.ServerFactory factory = entry.getValue();
				return factory.getServerElement(player, world, x, y, z);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		Iterator<Map.Entry<GuiManager.ClientFactory, GuiManager.ServerFactory>> iterator = GuiManager.GUI_MAP.entrySet().iterator();
		for (int i = 0; iterator.hasNext(); i++)
		{
			if (ID == i)
			{
				Map.Entry<GuiManager.ClientFactory, GuiManager.ServerFactory> entry = iterator.next();
				GuiManager.ClientFactory factory = entry.getKey();
				return factory.getClientElement(player, world, x, y, z);
			}
		}
		return null;
	}
}