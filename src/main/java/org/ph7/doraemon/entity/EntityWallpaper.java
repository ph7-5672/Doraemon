package org.ph7.doraemon.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityWallpaper extends EntityBase
{
    private static final DataParameter<BlockPos> ROOM_POS = EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.BLOCK_POS);


    public EntityWallpaper(World worldIn)
    {
        super(worldIn);
        this.setSize(0.625F, 0.625F);
    }

    @Override
    protected ItemStack getDropItem()
    {
        return null;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(ROOM_POS, BlockPos.ORIGIN);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("RoomPos"))
        {
            int[] r = compound.getIntArray("RoomPos");
            this.setRoomPos(new BlockPos(r[0], r[1], r[2]));
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        BlockPos pos = this.getRoomPos();
        compound.setIntArray("RoomPos", new int[]{pos.getX(), pos.getY(), pos.getZ()});
    }

    public BlockPos getRoomPos()
    {
        return this.dataManager.get(ROOM_POS);
    }

    public void setRoomPos(BlockPos pos)
    {
        this.dataManager.set(ROOM_POS, pos);
    }

}
