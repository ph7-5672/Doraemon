package org.ph7.doraemon.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.ph7.doraemon.common.ItemUtil;

public class ItemFishingRodAuto extends ItemFishingRod
{
    public ItemFishingRodAuto()
    {
        super();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemUtil.setTagBoolean(playerIn.getHeldItem(handIn), "auto_fish", playerIn.fishEntity == null);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (worldIn.isRemote) return;

        if (itemSlot == 0 || isSelected)
        {
            if (entityIn instanceof EntityPlayer)
            {
                if (ItemUtil.getTagBoolean(stack, "auto_fish"))
                {
                    EntityPlayer player = (EntityPlayer) entityIn;
                    EntityFishHook fishEntity = player.fishEntity;
                    if (fishEntity != null)
                    {
                        if (fishEntity.isInWater() && fishEntity.motionY < -0.4F * 0.6F && fishEntity.motionY >= -0.4F)
                        {
                            super.onItemRightClick(worldIn, player, player.getActiveHand());
                            ItemUtil.setTagInt(stack, "auto_fish_tick", 0);
                        }
                    }
                    else
                    {
                        int tick = ItemUtil.getTagInt(stack, "auto_fish_tick");
                        if (tick > 10)
                        {
                            super.onItemRightClick(worldIn, player, player.getActiveHand());
                            tick = 0;
                        }
                        ItemUtil.setTagInt(stack, "auto_fish_tick", ++tick);
                    }

                }
            }
        }
    }


}
