package org.ph7.doraemon.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.model.ModelBambooDragonfly;
import javax.annotation.Nullable;
import java.util.List;

/**
 * 竹蜻蜓
 */
public class ItemBambooDragonfly extends ItemArmor
{
    public ItemBambooDragonfly()
    {
        super(ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD);
    }

    /*@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;
            player.capabilities.allowFlying = itemSlot == 3;
            player.capabilities.isFlying = itemSlot == 3;
        }
    }*/

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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }
}
