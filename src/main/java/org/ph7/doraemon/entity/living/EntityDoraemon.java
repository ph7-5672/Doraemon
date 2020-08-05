package org.ph7.doraemon.entity.living;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import org.ph7.doraemon.common.GuiManager;
import org.ph7.doraemon.common.ParticleUtil;
import org.ph7.doraemon.gui.GuiDoraemon;
import org.ph7.doraemon.item.food.ItemDorayaki;

public class EntityDoraemon extends EntityLiving
{

    public EntityDoraemon(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        //手持铜锣烧时
        if (stack.getItem() instanceof ItemDorayaki)
        {
            //哆啦A梦相关操作
            //回血
            this.heal(3.0f);
            //爱心特效
            ParticleUtil.spawnParticles(EnumParticleTypes.HEART, this.world, this.getPosition().up(), 10);
            //todo 哆啦A梦表情
        }

        //按住shift时
        if (player.isSneaking())
        {
            //打开Gui
            GuiManager.openGui(1, player, player.world, 0, 0, 0);
        }
        return true;
    }
}
