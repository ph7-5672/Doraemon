package org.ph7.doraemon.gui.inventory;


public class InventoryShop extends InventoryBase
{
    public InventoryShop(int size)
    {
        super(size);
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public String getName()
    {
        return "tile.shop.name";
    }
}
