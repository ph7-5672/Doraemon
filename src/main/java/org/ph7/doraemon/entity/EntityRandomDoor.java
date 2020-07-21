package org.ph7.doraemon.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.common.GuiManager;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.gui.GuiRandomDoor;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.network.GuiPacket;

import javax.annotation.Nullable;
import java.util.List;

public class EntityRandomDoor extends EntityBase
{
    private static DataParameter<Boolean> OPEN = EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.BOOLEAN);
    private static DataParameter<BlockPos> TRANS = EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.BLOCK_POS);

    public EntityRandomDoor(World worldIn)
    {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(0.6F, 2.0F);
    }

    public EntityRandomDoor(World world, boolean isOpen)
    {
        this(world);
        this.setOpen(isOpen);
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isOpen()? null : this.getEntityBoundingBox();
    }



    @Override
    protected void entityInit()
    {
        this.dataManager.register(OPEN, Boolean.FALSE);
        this.dataManager.register(TRANS, BlockPos.ORIGIN);
    }

    @Override
    protected ItemStack getDropItem()
    {
        return new ItemStack(ModItems.RANDOM_DOOR);
    }

    @Override
    protected boolean canFitPassenger(Entity passenger)
    {
        return super.canFitPassenger(passenger);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("Open"))
        {
            this.setOpen(compound.getBoolean("Open"));
        }
        if (compound.hasKey("Trans"))
        {
            int[] trans = compound.getIntArray("Trans");
            this.setTrans(trans[0], trans[1], trans[2]);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("Open", this.isOpen());

        BlockPos trans = this.getTrans();
        compound.setIntArray("Trans", new int[]{trans.getX(), trans.getY(), trans.getZ()});

    }

    public void setOpen(boolean value)
    {
        this.dataManager.set(OPEN, value);
    }

    public boolean isOpen()
    {
        return this.dataManager.get(OPEN);
    }

    public void setTrans(int x, int y, int z)
    {
        this.setTrans(new BlockPos(x,y,z));
    }

    public void setTrans(BlockPos pos)
    {
        this.dataManager.set(TRANS, pos);
    }

    public BlockPos getTrans()
    {
        return this.dataManager.get(TRANS);
    }

    public EntityRandomDoor getCopy()
    {
        BlockPos trans = this.getTrans();
        List<? extends EntityRandomDoor> entities = this.world.getEntities(this.getClass(), x -> x.getPosition().equals(trans));
        if (entities.isEmpty()) return null;

        return entities.get(0);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), EntitySelectors.getTeamCollisionPredicate(this));
        BlockPos trans = this.getTrans();
        if (!list.isEmpty())
        {
            if (!trans.equals(this.getPosition()) && this.isOpen())
            {
                for (Entity entity : list)
                {
                    //通过motion计算出传送后的位置，至少隔一个单位，避免反复传送
                    double x = trans.getX();
                    double y = trans.getY();
                    double z = trans.getZ();
                    if (Math.abs(entity.motionX) >= Math.abs(entity.motionZ))
                    {
                        x = entity.motionX > 0 ? ++x : --x;
                    }
                    else
                    {
                        z = entity.motionZ > 0 ? ++z : --z;
                    }
                    entity.setPosition(x, y, z);

                }
            }
        }
    }

    /**
     * 右键互动
     * @param player
     * @param hand
     * @return
     */
    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking())
        {
            //Doraemon.NETWORK.sendToServer(new GuiPacket(this.getEntityId(), 0, 0));
            GuiManager.openGui(GuiRandomDoor.class, player, player.world, this.getEntityId(), 0, 0);
        }
        else
        {
            this.setOpen(!this.isOpen());
            EntityRandomDoor copy = this.getCopy();
            if (copy != null) copy.setOpen(this.isOpen());
        }
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            if (!this.world.isRemote)
            {
                EntityRandomDoor copy = (EntityRandomDoor) this.getCopy();
                if (copy != null) copy.setDead();
                this.setTrans(this.getPosition());
            }
            return true;
        }
        return false;
    }

    public EntityRandomDoor copy()
    {
        EntityRandomDoor copy = new EntityRandomDoor(this.world);
        copy.rotationYaw = this.rotationYaw;
        BlockPos trans = this.getTrans();
        copy.setPosition(trans.getX(), trans.getY(), trans.getZ());
        copy.setTrans(this.getPosition());
        copy.setOpen(this.isOpen());
        return copy;
    }
}
