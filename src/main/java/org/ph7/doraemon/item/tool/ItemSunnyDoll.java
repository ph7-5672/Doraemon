package org.ph7.doraemon.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntitySunnyDoll;
import javax.annotation.Nullable;
import java.util.List;

public class ItemSunnyDoll extends Item
{

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing == EnumFacing.DOWN)
        {
            EntitySunnyDoll entitySunnyDoll = new EntitySunnyDoll(worldIn);
            float x = pos.getX() + hitX;
            float y = pos.getY() + hitY;
            float z = pos.getZ() + hitZ;
            entitySunnyDoll.setPosition(x, y, z);
            entitySunnyDoll.rotationYaw = player.rotationYaw;
            if (!worldIn.isRemote)
            {
                worldIn.spawnEntity(entitySunnyDoll);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }
}
