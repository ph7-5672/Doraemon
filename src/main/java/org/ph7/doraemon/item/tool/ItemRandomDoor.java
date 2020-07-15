package org.ph7.doraemon.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityRandomDoor;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRandomDoor extends Item
{

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing == EnumFacing.UP)
        {
            ItemStack itemstack = player.getHeldItem(hand);
            float x = pos.getX() + hitX;
            float y = pos.getY() + hitY;
            float z = pos.getZ() + hitZ;
            EntityRandomDoor door = new EntityRandomDoor(world, false);
            door.setPosition(x, y, z);
            door.setTrans(door.getPosition());
            door.rotationYaw = player.rotationYaw;
            if (!world.isRemote)
            {
                world.spawnEntity(door);
            }

            if (world.getCollisionBoxes(door, door.getEntityBoundingBox().grow(-0.1D)).isEmpty())
            {
                if (!world.isRemote)
                {
                    world.spawnEntity(door);
                }

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                player.addStat(StatList.getObjectUseStats(this));
                return EnumActionResult.SUCCESS;
            }

        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }

}
