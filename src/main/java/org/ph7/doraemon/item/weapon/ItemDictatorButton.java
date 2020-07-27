package org.ph7.doraemon.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.ph7.doraemon.item.ItemBase;

/**
 * 独裁者按钮
 */
public class ItemDictatorButton extends ItemBase
{
    public ItemDictatorButton()
    {
        this.setMaxStackSize(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target.world.isRemote)
        {
            return false;
        }
        target.world.playEvent(2000, target.getPosition(), 0);
        target.setDead();
        return true;
    }



}
