package org.ph7.doraemon.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.item.ItemBase;
import org.ph7.doraemon.model.ModelBambooDragonfly;

import javax.annotation.Nullable;

/**
 * 竹蜻蜓
 */
public class ItemBambooDragonfly extends ArmorBase
{
    public ItemBambooDragonfly()
    {
        super("bamboo_dragonfly", ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!player.capabilities.isCreativeMode)
            {
                player.capabilities.isFlying = player.capabilities.allowFlying = itemSlot == 3;
            }
        }
    }

    /*public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
        return EntityEquipmentSlot.HEAD.equals(armorType);
    }*/

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/bamboo_dragonfly.png").toString();
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        return new ModelBambooDragonfly();
    }
}
