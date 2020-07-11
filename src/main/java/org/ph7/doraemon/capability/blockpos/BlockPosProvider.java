package org.ph7.doraemon.capability.blockpos;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPosProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IBlockPosCapability.class)
    public static Capability<IBlockPosCapability> BLOCK_POS_CAP = null;

    private IBlockPosCapability instance = BLOCK_POS_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == BLOCK_POS_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == BLOCK_POS_CAP ? BLOCK_POS_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return BLOCK_POS_CAP.getStorage().writeNBT(BLOCK_POS_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        BLOCK_POS_CAP.getStorage().readNBT(BLOCK_POS_CAP, this.instance, null, nbt);
    }

    public IBlockPosCapability getInstance()
    {
        return instance;
    }
}
