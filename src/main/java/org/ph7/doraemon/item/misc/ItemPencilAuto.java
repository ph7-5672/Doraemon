package org.ph7.doraemon.item.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.item.ItemBase;
import org.ph7.doraemon.item.food.ItemMemoryBread;

public class ItemPencilAuto extends ItemBase
{

    public ItemPencilAuto()
    {
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        NBTTagList memory = ItemMemoryBread.getPlayerMemory(player);
        if (memory != null && memory.tagCount() > 0)
        {
            //判断另一只手是否普通书本
            ItemStack book = this.getHeldBook(player);
            if (!book.isEmpty())
            {
                player.setActiveHand(hand);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            NBTTagList memory = ItemMemoryBread.getPlayerMemory(player);
            if (memory != null && memory.tagCount() > 0)
            {
                ItemStack book = this.getHeldBook(player);
                if (!book.isEmpty())
                {
                    //书本附魔
                    ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                    ItemUtil.addBookEnchantments(enchantedBook, memory);
                    //消耗原本书，添加附魔书
                    book.shrink(1);
                    //清空记忆
                    ItemMemoryBread.clearPlayerMemory(player);
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
        return EnumAction.DRINK;
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



    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

}
