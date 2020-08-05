package org.ph7.doraemon.item.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import org.ph7.doraemon.item.ItemBase;

/**
 * 恶魔护照
 */
public class ItemSatanPassport extends ItemBase
{

    public static final DataParameter<Integer> GAME_TYPE = EntityDataManager.createKey(EntityPlayer.class, DataSerializers.VARINT);

   /* @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;
            GameType currentGameType = Minecraft.getMinecraft().playerController.getCurrentGameType();
            if (!currentGameType.isCreative() && isSelected)
            {
                player.setGameType(GameType.CREATIVE);
                if (currentGameType != GameType.CREATIVE)
                {
                    player.getDataManager().set(GAME_TYPE, currentGameType.getID());
                }

            }
        }
    }*/


}
