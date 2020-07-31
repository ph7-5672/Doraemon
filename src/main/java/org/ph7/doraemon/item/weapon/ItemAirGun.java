package org.ph7.doraemon.item.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.item.ItemBase;
import org.ph7.doraemon.render.RenderBase;

import javax.annotation.Nullable;


public class ItemAirGun extends ItemBase
{

    public ItemAirGun()
    {
        this.setMaxStackSize(1);
        /*this.addPropertyOverride(new ResourceLocation(Reference.MOD_ID, "cast"), (stack, world, entity) ->
        {
            if (entity.getActiveItemStack().getItem() instanceof ItemAirGun)
            {
                if (entity.getItemInUseCount() > 0) return 1.0f;
            }
            return 0.0f;
        });*/
    }

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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    /**
     * 松开鼠标右键时调用
     * @param stack
     * @param world
     * @param entityLiving
     * @param timeLeft
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft)
    {

        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (!world.isRemote)
            {
                //蓄力时间与攻击力正比
                float damage = MathHelper.clamp(timeLeft * 1.5f, 1f, 10f);
                shoot(player, world, damage);
            }
        }
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    public static void shoot(EntityPlayer player, World world, float damage)
    {
        ItemAirGun.EntityAirBullet bullet = new ItemAirGun.EntityAirBullet(world, player);
        bullet.setDamage(damage);
        float rotationYaw = player.rotationYaw;
        bullet.shoot(player, player.rotationPitch, rotationYaw, 0.0F, 3.0F, 0.0F);
        world.spawnEntity(bullet);
        EnumFacing facing = EnumFacing.fromAngle(rotationYaw);
        int data = facing.getFrontOffsetX() + 1 + (facing.getFrontOffsetZ() + 1) * 3;
        for (int i = 0; i < 10; i++)
        {
            world.playEvent(2000, player.getPosition().up(), data);
        }
    }

    public static class EntityAirBullet extends EntityThrowable
    {
        private float damage;

        public EntityAirBullet(World worldIn)
        {
            super(worldIn);
        }

        public EntityAirBullet(World worldIn, EntityLivingBase throwerIn)
        {
            super(worldIn, throwerIn);
        }

        @Override
        protected void onImpact(RayTraceResult result)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
            }

            if (!this.world.isRemote)
            {
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }
        }

        public float getDamage() {
            return damage;
        }

        public void setDamage(float damage) {
            this.damage = damage;
        }
    }

    public static class RenderAirBullet extends RenderBase<EntityAirBullet>
    {

        public RenderAirBullet(RenderManager renderManager)
        {
            super(renderManager);
        }

        @Override
        protected ModelBase getModel()
        {
            return new ModelBase()
            {

            };
        }

        @Nullable
        @Override
        protected ResourceLocation getEntityTexture(EntityAirBullet entity)
        {
            return null;
        }
    }

}
