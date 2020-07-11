package org.ph7.doraemon.render.tile;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import org.ph7.doraemon.capability.blockpos.BlockPosProvider;
import org.ph7.doraemon.capability.blockpos.IBlockPosCapability;
import org.ph7.doraemon.tile.TileEntityRandomDoor;

public class TileEntityRandomDoorRenderer extends FastTESR<TileEntityRandomDoor>
{
    @Override
    public void renderTileEntityFast(TileEntityRandomDoor te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        IBlockPosCapability capability = te.getCapability(BlockPosProvider.BLOCK_POS_CAP, null);
        BlockPos pos = capability.getBlockPos();
        GlStateManager.popMatrix();
    }
}
