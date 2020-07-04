package org.ph7.doraemon.item.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.ph7.doraemon.core.ClientProxy;
import org.ph7.doraemon.gui.inventory.InventoryDimensionalPocket;

public class ItemDimensionalPocket extends ItemArmor
{

    public ItemDimensionalPocket()
    {
        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST);
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if (!world.isRemote && ClientProxy.openDimensionalPocket.isPressed())
        {
            player.displayGUIChest(InventoryDimensionalPocket.getInstance(world));
        }
    }
}
