package org.ph7.doraemon.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityWallpaper;
import javax.annotation.Nullable;
import java.util.List;

/**
 * 壁纸房
 */
public class ItemWallpaperHouse extends Item
{

    protected int width;
    protected int height;

    public ItemWallpaperHouse(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
        {
            if (!worldIn.isRemote)
            {
                EntityWallpaper wallPaper = new EntityWallpaper(worldIn);
                wallPaper.setPosition(pos.getX(), pos.getY(), pos.getZ());
                worldIn.spawnEntity(wallPaper);
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(this.getUnlocalizedName() + ".desc").getUnformattedComponentText());
    }

}
