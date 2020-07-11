package org.ph7.doraemon.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLightShrink extends Item
{

    public ItemLightShrink()
    {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    /*@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target.world.isRemote)
        {
            return false;
        }
        playerIn.swingArm(hand);
        target.width /= 2;
        target.height /= 2;
        ModelBase model = EntityUtil.getLivingModel(target.getClass());
        List<ModelRenderer> boxList = model.boxList;
        for (ModelRenderer renderer : boxList)
        {

        }
        return true;
    }*/

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }

}
