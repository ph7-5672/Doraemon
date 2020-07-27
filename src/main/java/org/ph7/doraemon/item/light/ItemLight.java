package org.ph7.doraemon.item.light;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.ph7.doraemon.common.EntityUtil;
import org.ph7.doraemon.item.ItemSubBase;
import java.util.ArrayList;
import java.util.List;

public class ItemLight extends ItemSubBase
{

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        int meta = stack.getMetadata();
        float widthScale = EntityUtil.getWidthScale(target);
        float heightScale = EntityUtil.getHeightScale(target);
        if (meta == 0)
        {
            if (widthScale > 0.5F)
            {
                EntityUtil.scale(target, widthScale * 0.5F, heightScale * 0.5F);
            }
        }
        else if (meta == 1)
        {
            if (widthScale < 2.0F)
            {
                EntityUtil.scale(target, widthScale * 2.0F, heightScale * 2.0F);
            }
        }

        return true;
    }

    @Override
    public List<String> getSubNames()
    {
        ArrayList<String> names = Lists.newArrayList();
        names.add("shrink");
        names.add("grow");
        return names;
    }

}
