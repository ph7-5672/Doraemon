package org.ph7.doraemon.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.init.ModPotions;
import org.ph7.doraemon.item.ItemFoodBase;
import org.ph7.doraemon.potion.PotionMemory;
import javax.annotation.Nullable;
import java.util.List;

/**
 * 记忆面包
 */
public class ItemMemoryBread extends ItemFoodBase
{

    public ItemMemoryBread()
    {
        super(0, 1f, false);
        this.maxStackSize = 1;
        this.setAlwaysEdible();
        this.addPropertyOverride(new ResourceLocation(Reference.MOD_ID, "memory"), (stack, world, entity) ->
            this.canEat(stack) ? 1.0f : 0.0f
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        if (this.canEat(stack))
        {
            return super.onItemRightClick(world, player, hand);
        }
        //判断另一只手是否附魔书
        ItemStack stack1 = player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack stack2 = player.getHeldItem(EnumHand.OFF_HAND);
        ItemStack book = ItemStack.EMPTY;
        if (stack1.getItem() instanceof ItemEnchantedBook) book = stack1;
        if (stack2.getItem() instanceof ItemEnchantedBook) book = stack2;
        if (!book.isEmpty())
        {
            //复制附魔书内容
            NBTTagList enchantments = ItemEnchantedBook.getEnchantments(book);
            ItemUtil.addBookEnchantments(stack, enchantments);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return this.canEat(stack) ? 32 : 0;
    }

    /**
     * 通过是否有记忆内容判断可否食用
     * @param stack
     * @return
     */
    protected boolean canEat(ItemStack stack)
    {
        NBTTagList memory = this.getMemory(stack);
        return memory.tagCount() > 0;
    }

    protected NBTTagList getMemory(ItemStack stack)
    {
        return ItemEnchantedBook.getEnchantments(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagList nbttaglist = this.getMemory(stack);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getShort("id");
            Enchantment enchantment = Enchantment.getEnchantmentByID(j);

            if (enchantment != null)
            {
                tooltip.add(enchantment.getTranslatedName(nbttagcompound.getShort("lvl")));
            }
        }
    }

    /**
     * 食用后为player添加属性
     * @param stack
     * @param world
     * @param player
     */
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
        //添加记忆效果，限时5分钟
        NBTTagList memory = this.getMemory(stack);
        ModPotions.MEMORY.addMemory(player, memory);
    }


}
