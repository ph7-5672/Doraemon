package org.ph7.doraemon.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFoodBase extends ItemFood
{
    public ItemFoodBase(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.setMaxDamage(0);
    }

    public ItemFoodBase(int amount, boolean isWolfFood)
    {
        super(amount, isWolfFood);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(stack.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }
}
