package org.ph7.doraemon.item.room;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.item.ItemBase;
import org.ph7.doraemon.item.ItemEntityBase;

public class ItemRandomDoor extends ItemEntityBase
{

    @Override
    protected Entity getEntity(World world)
    {
        return new EntityRandomDoor(world, false);
    }

    @Override
    protected boolean canSpawn(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Entity entity)
    {
        IBlockState state = world.getBlockState(pos.up());
        return super.canSpawn(player, world, pos, hand, facing, entity)
                && state.getMaterial() == Material.AIR
                && world.getCollisionBoxes(entity, entity.getEntityBoundingBox().grow(-0.1D)).isEmpty()
                && facing == EnumFacing.UP;
    }
}
