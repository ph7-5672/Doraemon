package org.ph7.doraemon.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemEntityBase extends ItemBase
{

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Entity entity = this.getEntity(worldIn, pos);
        ItemStack itemStack = player.getHeldItem(hand);
        if (entity != null)
        {
            float x = pos.getX() + hitX;
            float y = pos.getY() + hitY;
            float z = pos.getZ() + hitZ;
            entity.setPosition(x, y, z);

            if (this.isFacingPlayer())
            {
                entity.rotationYaw = player.rotationYaw;
            }
            if (this.canSpawn(player, worldIn, pos, hand, facing, entity))
            {
                worldIn.spawnEntity(entity);
                itemStack.shrink(1);
                player.addStat(StatList.getObjectUseStats(this));
            }

        }
        return EnumActionResult.SUCCESS;
    }

    protected abstract Entity getEntity(World world, BlockPos pos);

    protected boolean isFacingPlayer()
    {
        return true;
    }

    protected boolean canSpawn(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Entity entity)
    {
        return !world.isRemote;
    }

}
