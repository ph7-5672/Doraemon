package org.ph7.doraemon.item.tool;


import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.item.ItemToolBase;

import java.util.Set;

/**
 * 掘地手套
 */
public class ItemDigGlove extends ItemToolBase
{

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
            Blocks.CLAY,
            Blocks.DIRT,
            Blocks.FARMLAND,
            Blocks.GRASS,
            Blocks.GRAVEL,
            Blocks.MYCELIUM,
            Blocks.SAND,
            Blocks.SNOW,
            Blocks.SNOW_LAYER,
            Blocks.SOUL_SAND,
            Blocks.GRASS_PATH,
            Blocks.CONCRETE_POWDER,
            Blocks.ACTIVATOR_RAIL,
            Blocks.COAL_ORE,
            Blocks.COBBLESTONE,
            Blocks.DETECTOR_RAIL,
            Blocks.DIAMOND_BLOCK,
            Blocks.DIAMOND_ORE,
            Blocks.DOUBLE_STONE_SLAB,
            Blocks.GOLDEN_RAIL,
            Blocks.GOLD_BLOCK,
            Blocks.GOLD_ORE,
            Blocks.ICE,
            Blocks.IRON_BLOCK,
            Blocks.IRON_ORE,
            Blocks.LAPIS_BLOCK,
            Blocks.LAPIS_ORE,
            Blocks.LIT_REDSTONE_ORE,
            Blocks.MOSSY_COBBLESTONE,
            Blocks.NETHERRACK,
            Blocks.PACKED_ICE,
            Blocks.RAIL,
            Blocks.REDSTONE_ORE,
            Blocks.SANDSTONE,
            Blocks.RED_SANDSTONE,
            Blocks.STONE,
            Blocks.STONE_SLAB,
            Blocks.STONE_BUTTON,
            Blocks.STONE_PRESSURE_PLATE
    );

    public ItemDigGlove()
    {
        super(1.0F, 5.0F, ToolMaterial.DIAMOND, EFFECTIVE_ON);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        return false;
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        int gear = ItemUtil.getTagInt(stack, "Gear");
        return (gear + 1) * 5.0F;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        int gear = ItemUtil.getTagInt(itemStack, "Gear") + 1;
        if (gear > 2)
        {
            gear = 0;
        }
        ItemUtil.setTagInt(itemStack, "Gear", gear);

        Minecraft.getMinecraft().ingameGUI.setOverlayMessage(I18n.format("dig_glove.info") + ":" + (gear+1), false);

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }
}
