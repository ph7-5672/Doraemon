package org.ph7.doraemon.item.misc;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.living.EntityDoraemon;
import org.ph7.doraemon.item.ItemEntityBase;

public class ItemDoraemon extends ItemEntityBase
{
    @Override
    protected Entity getEntity(World world, BlockPos pos)
    {
        return new EntityDoraemon(world);
    }
}
