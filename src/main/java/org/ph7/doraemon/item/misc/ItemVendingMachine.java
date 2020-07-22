package org.ph7.doraemon.item.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityVendingMachine;
import org.ph7.doraemon.item.ItemEntityBase;

public class ItemVendingMachine extends ItemEntityBase
{
    @Override
    protected Entity getEntity(World world, BlockPos pos)
    {
        return new EntityVendingMachine(world);
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
