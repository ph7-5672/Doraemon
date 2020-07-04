package org.ph7.doraemon.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.ph7.doraemon.common.IHasGui;
import org.ph7.doraemon.core.ClientProxy;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		for (IHasGui obj : ClientProxy.GUI_LIST)
		{
			if (ID == obj.getGuiId())
			{
				return obj.getServerGuiElement(player, world, x, y, z);
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		for (IHasGui obj : ClientProxy.GUI_LIST)
		{
			if (ID == obj.getGuiId())
			{
				return obj.getClientGuiElement(player, world, x, y, z);
			}
		}

		return null;
	}
}