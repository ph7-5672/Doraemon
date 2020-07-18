package org.ph7.doraemon.item.weather;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityThunderCloud;
import org.ph7.doraemon.item.ItemEntityBase;

/**
 * todo 打雷的云
 * 创建实体
 * 拉一下放一道雷
 */
public class ItemThunderCloud extends ItemEntityBase
{

    @Override
    protected Entity getEntity(World world)
    {
        return new EntityThunderCloud(world);
    }

    @Override
    protected boolean canSpawn(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Entity entity)
    {
        IBlockState state = world.getBlockState(entity.getPosition());
        return super.canSpawn(player, world, pos, hand, facing, entity)
                && state.getMaterial()== Material.AIR;
    }
}
