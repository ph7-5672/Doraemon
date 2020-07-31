package org.ph7.doraemon.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.init.ModPotions;
import org.ph7.doraemon.item.ItemBase;
import org.ph7.doraemon.potion.PotionMemory;

public class ItemPencilAuto extends ItemBase
{

    public ItemPencilAuto()
    {
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        //判断另一只手是否普通书本
        ItemStack book = this.getHeldBook(player);
        if (book.isEmpty())
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }

        player.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            PotionMemory memory = ModPotions.MEMORY;
            PotionEffect effect = player.getActivePotionEffect(memory);
            if (effect != null && effect.getDuration() > 0)
            {
                ItemStack book = this.getHeldBook(player);
                if (!book.isEmpty())
                {
                    //书本附魔
                    ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                    ItemUtil.addBookEnchantments(enchantedBook, memory.getMemory(player));
                    //消耗原本书，添加附魔书
                    book.shrink(1);
                    if (!player.inventory.addItemStackToInventory(enchantedBook))
                    {
                        player.dropItem(enchantedBook, false);
                    }
                }
            }
        }
        return stack;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    protected ItemStack getHeldBook(EntityPlayer player)
    {
        ItemStack stack1 = player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack stack2 = player.getHeldItem(EnumHand.OFF_HAND);
        ItemStack book = ItemStack.EMPTY;
        if (stack1.getItem() instanceof ItemBook) book = stack1;
        if (stack2.getItem() instanceof ItemBook) book = stack2;
        return book;
    }

}
