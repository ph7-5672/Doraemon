package org.ph7.doraemon.capability.blockpos;

import net.minecraft.util.math.BlockPos;

public class BlockPosCapabilityImpl implements IBlockPosCapability
{
    private BlockPos pos;

    public static IBlockPosCapability instance;

    @Override
    public BlockPos getBlockPos()
    {
        return this.pos;
    }

    @Override
    public void setBlockPos(BlockPos pos)
    {
        this.pos = pos;
    }

    public static IBlockPosCapability create()
    {
        if (instance == null)
        {
            synchronized (BlockPosCapabilityImpl.class)
            {
                if (instance == null)
                {
                    instance = new BlockPosCapabilityImpl();
                }
            }
        }
        return instance;
    }
}
