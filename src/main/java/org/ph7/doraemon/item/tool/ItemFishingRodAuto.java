package org.ph7.doraemon.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.common.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

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

        if (entityIn instanceof EntityPlayer)
        {
            ItemStack stack1 = ((EntityPlayer) entityIn).getHeldItem(EnumHand.MAIN_HAND);
            ItemStack stack2 = ((EntityPlayer) entityIn).getHeldItem(EnumHand.OFF_HAND);
            if (stack1.getItem() instanceof ItemFishingRodAuto || stack2.getItem() instanceof ItemFishingRodAuto)
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
            else
            {
                ItemUtil.setTagBoolean(stack, "auto_fish", false);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }
}
