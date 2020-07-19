package org.ph7.doraemon.item.weather;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityWeatherBox;
import org.ph7.doraemon.item.ItemEntityBase;


public class ItemWeatherBox extends ItemEntityBase
{
    @Override
    protected Entity getEntity(World world, BlockPos pos)
    {
        return new EntityWeatherBox(world);
    }

    @Override
    protected boolean canSpawn(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Entity entity)
    {
        return super.canSpawn(player, world, pos, hand, facing, entity)
                && world.getCollisionBoxes(entity, entity.getEntityBoundingBox().grow(-0.1D)).isEmpty()
                && facing == EnumFacing.UP;
    }
}
