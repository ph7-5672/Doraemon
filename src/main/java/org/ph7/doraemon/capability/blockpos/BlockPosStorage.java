package org.ph7.doraemon.capability.blockpos;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class BlockPosStorage implements Capability.IStorage<IBlockPosCapability>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBlockPosCapability> capability, IBlockPosCapability instance, EnumFacing side)
    {
        BlockPos blockPos = instance.getBlockPos();
        NBTTagCompound nbt = new NBTTagCompound();
        if (blockPos != null)
        {
            nbt.setIntArray("Doraemon:BlockPos", new int[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
        }
        return nbt;
    }

    @Override
    public void readNBT(Capability<IBlockPosCapability> capability, IBlockPosCapability instance, EnumFacing side, NBTBase nbt)
    {
        if (nbt instanceof NBTTagCompound)
        {
            int[] array = ((NBTTagCompound) nbt).getIntArray("Doraemon:BlockPos");
            if (array != null && array.length > 0)
            {
                instance.setBlockPos(new BlockPos(array[0], array[1], array[2]));
            }
        }

    }
}
