package org.ph7.doraemon.capability.blockpos;

import net.minecraft.util.math.BlockPos;

public interface IBlockPosCapability
{
    BlockPos getBlockPos();

    void setBlockPos(BlockPos pos);

}
