package org.ph7.doraemon.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.entity.EntityAirBullet;


public class ItemAirGun extends Item
{

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
    {
        if (!worldIn.isRemote)
        {
            EntityAirBullet bullet = new EntityAirBullet(worldIn, player);
            bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 10F, 0F);
            worldIn.spawnEntity(bullet);
            player.swingArm(handIn);
            //todo 后座力
            //player.move();

        }

        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }*/

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            EntityAirBullet bullet = new EntityAirBullet(worldIn, player);
            bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 10F, 0F);
            worldIn.spawnEntity(bullet);
            super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        }
       
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
    {
        ItemStack itemstack = player.getHeldItem(handIn);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, player, handIn, true);
        if (ret != null) return ret;
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
