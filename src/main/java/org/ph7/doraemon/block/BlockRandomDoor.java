package org.ph7.doraemon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.ph7.doraemon.capability.blockpos.BlockPosProvider;
import org.ph7.doraemon.capability.blockpos.IBlockPosCapability;
import org.ph7.doraemon.tile.TileEntityRandomDoor;
import javax.annotation.Nullable;


public class BlockRandomDoor extends Block implements ITileEntityProvider
{

    public static final PropertyEnum<BlockDoor.EnumDoorHalf> HALF = PropertyEnum.<BlockDoor.EnumDoorHalf>create("half", BlockDoor.EnumDoorHalf.class);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = PropertyBool.create("open");

    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);

    public BlockRandomDoor()
    {
        super(new Material(MapColor.PINK));
        this.setHardness(3.0F);
        this.disableStats();
        //this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        boolean flag = !((Boolean)state.getValue(OPEN)).booleanValue();
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        switch (enumfacing)
        {
            case EAST:
            default:
                return flag ? EAST_AABB : NORTH_AABB;
            case SOUTH:
                return flag ? SOUTH_AABB : EAST_AABB;
            case WEST:
                return flag ? WEST_AABB : SOUTH_AABB;
            case NORTH:
                return flag ? NORTH_AABB : WEST_AABB;
        }
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public String getLocalizedName()
    {
        return I18n.translateToLocal((this.getUnlocalizedName() + ".name").replaceAll("tile", "item"));
    }


    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF, FACING, OPEN});
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            i |= 8;
        }

        if (state.getValue(OPEN))
        {
            i |= 4;
        }

        return i | ((EnumFacing)state.getValue(FACING)).rotateY().getHorizontalIndex();
    }


    public IBlockState getStateFromMeta(int meta)
    {

        IBlockState state = this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW());

        if ((meta & 8) > 0)
        {
            state = state.withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER);
        }
        else
        {
            state = state.withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER);
        }

        return state.withProperty(OPEN, (meta & 4) > 0);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return this.getActualState(worldIn.getBlockState(pos), worldIn, pos).getValue(OPEN);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (worldIn.isRemote)
        {
            return;
        }
        if (state.getValue(OPEN))
        {
            return;
        }
        boolean flag = state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER;
        if (flag)
        {
            pos = pos.down();
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityRandomDoor) {

            IBlockPosCapability capability = te.getCapability(BlockPosProvider.BLOCK_POS_CAP, null);
            BlockPos to = capability.getBlockPos();
            if (to == null || to.equals(pos))
            {
                return;
            }
            //任意门也传送过去
            //ModItems.RANDOM_DOOR.placeDoor(worldIn, to, state.getValue(FACING), true);

            /*IBlockState state1 = worldIn.getBlockState(pos);
            IBlockState state2 = worldIn.getBlockState(up);
            BlockPos toUp = to.up();
            worldIn.setBlockState(to, state1, 2);
            worldIn.setBlockState(toUp, state2, 2);
            worldIn.notifyNeighborsOfStateChange(to, this, false);
            worldIn.notifyNeighborsOfStateChange(toUp, this, false);*/
            BlockPos up = pos.up();
            worldIn.setBlockToAir(pos);
            worldIn.setBlockToAir(up);
            //传送至目标位置
            entityIn.setPosition(to.getX(), to.getY(), to.getZ());
        }
    }

    /*
    public boolean isOpen(IBlockState blockState)
    {
        return this.getMetaFromState(blockState) == 1;
    }*/

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getValue(HALF) != BlockDoor.EnumDoorHalf.LOWER ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        if (pos.getY() >= worldIn.getHeight() - 1)
        {
            return false;
        }
        else
        {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return super.isReplaceable(worldIn, pos);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
            else if (blockIn != this)
            {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        }
        else
        {
            boolean flag1 = false;
            BlockPos blockpos1 = pos.up();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn,  pos.down(), EnumFacing.UP))
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate1.getBlock() == this)
                {
                    worldIn.setBlockToAir(blockpos1);
                }
            }

            if (flag1)
            {
                if (!worldIn.isRemote)
                {
                    this.dropBlockAsItem(worldIn, pos, state, 0);
                }
            }
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos blockpos = pos.down();
        BlockPos blockpos1 = pos.up();

        if (player.capabilities.isCreativeMode && state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
        {
            worldIn.setBlockToAir(blockpos);
        }

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
            {
                worldIn.setBlockToAir(pos);
            }

            worldIn.setBlockToAir(blockpos1);
        }
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());

            if (iblockstate.getBlock() == this)
            {
                state = state.withProperty(FACING, iblockstate.getValue(FACING)).withProperty(OPEN, iblockstate.getValue(OPEN));
            }
        }
        return state;

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
        IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() != this)
        {
            return false;
        }
        else
        {
            state = iblockstate.cycleProperty(OPEN);
            if (state.getValue(OPEN))
            {
                if (worldIn.getTileEntity(blockpos) instanceof TileEntityRandomDoor)
                {
                    //Doraemon.NETWORK.sendToServer(new GuiPacket());
                    //playerIn.openGui(Doraemon.instance, this.getGuiId(), worldIn, blockpos.getX(), blockpos.getY(), blockpos.getZ());
                }
            }

            worldIn.setBlockState(blockpos, state, 10);
            worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
            //worldIn.playEvent(playerIn, ((Boolean)state.getValue(OPEN)).booleanValue() ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return true;
        }

    }



    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityRandomDoor();
    }

}
