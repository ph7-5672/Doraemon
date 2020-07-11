package org.ph7.doraemon.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityRandomDoor;

import java.util.List;

public class ItemRandomDoor extends Item
{

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * 1.0F;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * 1.0F;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * 1.0D;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * 1.0D + (double)player.getEyeHeight();
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * 1.0D;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3d vec3d1 = vec3d.addVector((double)f7 * 5.0D, (double)f6 * 5.0D, (double)f8 * 5.0D);
        RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d, vec3d1, true);

        if (raytraceresult == null)
        {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }
        else
        {
            Vec3d vec3d2 = player.getLook(1.0F);
            boolean flag = false;
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(vec3d2.x * 5.0D, vec3d2.y * 5.0D, vec3d2.z * 5.0D).grow(1.0D));

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity = list.get(i);

                if (entity.canBeCollidedWith())
                {
                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());

                    if (axisalignedbb.contains(vec3d))
                    {
                        flag = true;
                    }
                }
            }

            if (flag)
            {
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
            else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
            {
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
            else
            {
                EntityRandomDoor door = new EntityRandomDoor(world, false);
                door.setPosition(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
                door.setTrans(door.getPosition());
                door.rotationYaw = player.rotationYaw;
                if (!world.isRemote)
                {
                    world.spawnEntity(door);
                }

                if (!world.getCollisionBoxes(door, door.getEntityBoundingBox().grow(-0.1D)).isEmpty())
                {
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
                else
                {
                    if (!world.isRemote)
                    {
                        world.spawnEntity(door);
                    }

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    player.addStat(StatList.getObjectUseStats(this));
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                }
            }
        }
    }
}
