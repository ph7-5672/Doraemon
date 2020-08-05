package org.ph7.doraemon.gui;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDoraemon extends GuiInventory
{
    public GuiDoraemon(EntityPlayer player)
    {
        super(player);
    }

}
