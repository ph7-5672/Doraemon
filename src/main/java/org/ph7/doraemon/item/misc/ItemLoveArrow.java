package org.ph7.doraemon.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityLoveArrow;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLoveArrow extends ItemArrow
{
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        return new EntityLoveArrow(worldIn, shooter);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }
}
