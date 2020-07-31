package org.ph7.doraemon.potion;

import com.google.common.collect.Maps;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.ph7.doraemon.init.ModItems;

import java.util.Map;

public class PotionMemory extends PotionBase
{
    public final Map<EntityPlayer, NBTTagList> playerMemory = Maps.newHashMap();

    public PotionMemory()
    {
        super(false, 0xfffae3);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        /*if (entityLivingBaseIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;

        }*/
    }

    public void addMemory(EntityPlayer player, NBTTagList memory)
    {
        player.addPotionEffect(new PotionEffect(this, 5 * 60 * 20));
        this.playerMemory.put(player, memory);
    }

    public NBTTagList getMemory(EntityPlayer player)
    {
        return this.playerMemory.get(player);
    }

}
