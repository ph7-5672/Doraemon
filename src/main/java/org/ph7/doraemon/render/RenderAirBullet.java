package org.ph7.doraemon.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.ph7.doraemon.entity.EntityAirBullet;

import javax.annotation.Nullable;

public class RenderAirBullet extends Render<EntityAirBullet>
{
    public RenderAirBullet(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAirBullet entity)
    {
        return null;
    }

    @Override
    public void doRender(EntityAirBullet entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        entity.world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
