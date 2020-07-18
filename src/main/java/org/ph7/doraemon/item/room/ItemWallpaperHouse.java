package org.ph7.doraemon.item.room;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ph7.doraemon.entity.EntityWallpaper;
import org.ph7.doraemon.item.ItemBase;

/**
 * 壁纸房
 */
public class ItemWallpaperHouse extends ItemBase
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

}
