package org.ph7.doraemon.entity;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.init.ModItems;
import javax.annotation.Nullable;

public class EntityScarecrow extends EntityBase
{

    public EntityScarecrow(World world)
    {
        super(world);
        this.setSize(0.6F, 2.2F);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        BlockPos pos = this.getPosition();
        World world = this.world;
        if (world.isRemote) return;

        Iterable<BlockPos.MutableBlockPos> all = BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 0, 4));
        for (BlockPos.MutableBlockPos p : all)
        {
            IBlockState state = world.getBlockState(p);
            if (state.getBlock() instanceof BlockCrops)
            {
                BlockCrops crops = (BlockCrops) state.getBlock();
                if (!crops.isMaxAge(state))
                {
                    crops.grow(world, p, state);
                }
            }
            else if (state.getBlock() instanceof BlockStem)
            {
                BlockStem stem = (BlockStem) state.getBlock();
                if (stem.canGrow(world, p, state, world.isRemote))
                {
                    stem.growStem(world, p, state);
                }
            }
        }

    }

    @Override
    protected ItemStack getDropItem()
    {
        return new ItemStack(ModItems.SCARECROW);
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
}
