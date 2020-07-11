package org.ph7.doraemon.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import org.ph7.doraemon.block.BlockRandomDoor;
import org.ph7.doraemon.capability.blockpos.BlockPosProvider;
import org.ph7.doraemon.init.ModBlocks;

import javax.annotation.Nullable;

public class TileEntityRandomDoor extends TileEntity implements ITickable
{
    @Override
    public void update()
    {
        IBlockState state = ModBlocks.RANDOM_DOOR.getStateFromMeta(this.getBlockMetadata());
        if (state.getValue(BlockRandomDoor.OPEN))
        {
            //todo 显示
            BlockPos pos = this.getPos();
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return BlockPosProvider.BLOCK_POS_CAP == capability || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        return super.getCapability(capability, facing);
    }

}
