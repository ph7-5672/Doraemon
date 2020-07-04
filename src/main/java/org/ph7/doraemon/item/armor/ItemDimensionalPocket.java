package org.ph7.doraemon.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.core.ClientProxy;
import org.ph7.doraemon.gui.inventory.InventoryDimensionalPocket;
import org.ph7.doraemon.model.ModelDimensionalPocket;

import javax.annotation.Nullable;

public class ItemDimensionalPocket extends ItemArmor
{
    private int storageSize = 27;

    public ItemDimensionalPocket()
    {
        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST);
        this.setMaxStackSize(1);
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if (!world.isRemote && ClientProxy.openDimensionalPocket.isPressed())
        {
            player.displayGUIChest(new InventoryDimensionalPocket(itemStack, storageSize));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote)
        {
            playerIn.displayGUIChest(new InventoryDimensionalPocket(heldItem, storageSize));
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/dimensional_pocket.png").toString();
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        return new ModelDimensionalPocket();
    }
}
