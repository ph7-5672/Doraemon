package org.ph7.doraemon.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.ph7.doraemon.item.weapon.ItemAirGun;

@Deprecated
public class PotionAirGun extends PotionBase
{
    public PotionAirGun()
    {
        super(false, 0x11111);
    }

    /**
     * 效果存在时的逻辑代码
     * @param entityLivingBaseIn
     * @param amplifier //等级
     */
    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        if (entityLivingBaseIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
            //判断空手
            ItemStack mainItem = player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offItem = player.getHeldItem(EnumHand.OFF_HAND);
            if (mainItem.isEmpty() && offItem.isEmpty())
            {
                //监听鼠标右键
                Minecraft mc = Minecraft.getMinecraft();
                if (mc.gameSettings.keyBindUseItem.isKeyDown())
                {
                    ItemAirGun.shoot(player, player.world, 5f);
                }
            }
        }
    }
}
