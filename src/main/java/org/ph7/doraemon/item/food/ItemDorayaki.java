package org.ph7.doraemon.item.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.ph7.doraemon.entity.living.EntityDoraemon;
import org.ph7.doraemon.item.ItemFoodBase;

/**
 * 铜锣烧
 */
public class ItemDorayaki extends ItemFoodBase
{
    public ItemDorayaki()
    {
        super(1, 2f, false);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target instanceof EntityDoraemon)
        {

            return true;
        }
        return false;
    }
}
