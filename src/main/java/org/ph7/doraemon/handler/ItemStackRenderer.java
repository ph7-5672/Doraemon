package org.ph7.doraemon.handler;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class ItemStackRenderer extends TileEntityItemStackRenderer
{
    public static ItemStackRenderer instance = new ItemStackRenderer();

    @Override
    public void renderByItem(ItemStack stack, float partialTicks)
    {
        super.renderByItem(stack, partialTicks);
    }
}
