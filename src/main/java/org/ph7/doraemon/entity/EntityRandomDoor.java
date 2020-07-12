package org.ph7.doraemon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityRandomDoor extends Entity
{
    private static DataParameter<Boolean> OPEN = EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.BOOLEAN);
    private static DataParameter<Integer>[] TRANS = new DataParameter[]{
            EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.VARINT),
            EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.VARINT),
            EntityDataManager.createKey(EntityRandomDoor.class, DataSerializers.VARINT)
    };

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
        this.dataManager.register(TRANS[0], 0);
        this.dataManager.register(TRANS[1], 0);
        this.dataManager.register(TRANS[2], 0);

        //this.setEntityBoundingBox(new AxisAlignedBB(0, 0, 0, 1D, 1D, 1D));
    }

    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    protected boolean canFitPassenger(Entity passenger)
    {
        return super.canFitPassenger(passenger);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.world.isRemote)
        {
            if (this.world.getGameRules().getBoolean("doEntityDrops"))
            {
                this.dropItemWithOffset(ModItems.RANDOM_DOOR, 1, 0.0F);
            }

            this.setDead();
        }

        return true;
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
        this.dataManager.set(TRANS[0], x);
        this.dataManager.set(TRANS[1], y);
        this.dataManager.set(TRANS[2], z);
    }

    public void setTrans(BlockPos pos)
    {
        this.setTrans(pos.getX(), pos.getY(), pos.getZ());
    }

    public BlockPos getTrans()
    {
        Integer x = this.dataManager.get(TRANS[0]);
        Integer y = this.dataManager.get(TRANS[1]);
        Integer z = this.dataManager.get(TRANS[2]);
        return new BlockPos(x, y, z);
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
                    entity.setPosition(trans.getX(), trans.getY(), trans.getZ());
                    //entity.addedToChunk
                }
            }
        }
    }

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
        }
        return true;
    }

}
