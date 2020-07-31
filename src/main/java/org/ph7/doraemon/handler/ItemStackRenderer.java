package org.ph7.doraemon.handler;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import org.ph7.doraemon.item.weapon.ItemAirGun;

public class ItemStackRenderer extends TileEntityItemStackRenderer
{
    public static ItemStackRenderer instance = new ItemStackRenderer();

    @Override
    public void renderByItem(ItemStack stack, float partialTicks)
    {
        super.renderByItem(stack, partialTicks);
    }
}
