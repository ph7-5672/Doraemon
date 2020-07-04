package org.ph7.doraemon.common;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public interface IHasGui
{
    int getGuiId();

    Gui getClientGuiElement(EntityPlayer player, World world, int x, int y, int z);

    Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z);
}
