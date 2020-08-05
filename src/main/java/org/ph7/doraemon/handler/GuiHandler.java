package org.ph7.doraemon.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.ph7.doraemon.common.GuiManager;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		GuiManager.Factory factory = GuiManager.factories.get(ID);
		if (factory == null) return null;
		return factory.getServerElement(player, world, x, y, z);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		GuiManager.Factory factory = GuiManager.factories.get(ID);
		if (factory == null) return null;
		return factory.getClientElement(player, world, x, y, z);
	}
}