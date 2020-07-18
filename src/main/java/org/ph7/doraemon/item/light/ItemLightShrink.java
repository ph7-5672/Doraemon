package org.ph7.doraemon.item.light;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.ph7.doraemon.item.ItemBase;

public class ItemLightShrink extends ItemBase
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


}
