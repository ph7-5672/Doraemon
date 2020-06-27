package org.ph7.doraemon.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.ItemUtil;

import javax.annotation.Nullable;

/**
 * 竹蜻蜓
 */
public class ItemBambooDragonfly extends ItemBase
{

    public ItemBambooDragonfly()
    {
        super("bamboo_dragonfly");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (entityIn instanceof EntityPlayer)
        {
            boolean isArmor = itemSlot == 3;
            ((EntityPlayer)entityIn).capabilities.allowFlying = isArmor;
            ((EntityPlayer)entityIn).capabilities.isFlying = isArmor;
        }
    }

    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
        return EntityEquipmentSlot.HEAD.equals(armorType);
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }


    @SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
        //todo 渲染模型
        return null;
    }

}
