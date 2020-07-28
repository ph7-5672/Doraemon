package org.ph7.doraemon.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemToolBase extends ItemTool
{
    protected ItemToolBase(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
    {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    protected ItemToolBase(ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
    {
        super(materialIn, effectiveBlocksIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(stack.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }

}
