package org.ph7.doraemon.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.ph7.doraemon.common.Reference;

import java.lang.reflect.Field;

public class ItemFishingRodAuto extends ItemFishingRod
{
    private int delay;
    private int ticks;

    public ItemFishingRodAuto()
    {
        super();
        this.delay = 5;
    }

    public void setFlag(ItemStack stack, boolean flag)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("flag", flag);
        stack.setTagCompound(tag);
    }

    public boolean getFlag(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.getBoolean("flag");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        this.setFlag(stack, playerIn.fishEntity == null);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (worldIn.isRemote)
        {
            return;
        }

        if (entityIn instanceof EntityPlayer && isSelected)
        {
            if (this.getFlag(stack))
            {
                EntityPlayer player = (EntityPlayer) entityIn;
                EntityFishHook fishEntity = player.fishEntity;
                try
                {
                    if (fishEntity != null)
                    {
                        Field field = EntityFishHook.class.getDeclaredField("ticksCatchable");
                        field.setAccessible(true);
                        Object o = field.get(fishEntity);
                        if (Integer.valueOf(o.toString()) > 0)
                        {
                            super.onItemRightClick(worldIn, player, player.getActiveHand());
                        }
                    }
                    else
                    {
                        ticks++;
                        if (ticks >= delay)
                        {
                            ticks = 0;
                            super.onItemRightClick(worldIn, player, player.getActiveHand());
                        }
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
    }


}
