package org.ph7.doraemon.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleUtil
{
    private static final Random rand = new Random();
    private static final ResourceLocation SNOW_TEXTURES = new ResourceLocation("textures/environment/snow.png");
    private static final float[] rainXCoords = new float[1024];
    private static final float[] rainYCoords = new float[1024];

    static
    {
        for (int i = 0; i < 32; ++i)
        {
            for (int j = 0; j < 32; ++j)
            {
                float f = (float)(j - 16);
                float f1 = (float)(i - 16);
                float f2 = MathHelper.sqrt(f * f + f1 * f1);
                rainXCoords[i << 5 | j] = -f1 / f2;
                rainYCoords[i << 5 | j] = f / f2;
            }
        }
    }

    /**
     * 复制来的雪花特效，在ClientThread中调用
     * @param partialTicks
     * @param rendererUpdateCount
     */
    public static void snow(float partialTicks, int rendererUpdateCount)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityRenderer entityRenderer = mc.entityRenderer;
        entityRenderer.enableLightmap();
        Entity entity = mc.getRenderViewEntity();
        World world = mc.world;
        int i = MathHelper.floor(entity.posX);
        int j = MathHelper.floor(entity.posY);
        int k = MathHelper.floor(entity.posZ);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.disableCull();
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(516, 0.1F);
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
        int l = MathHelper.floor(d1);
        int i1 = 5;

        if (mc.gameSettings.fancyGraphics)
        {
            i1 = 10;
        }

        int j1 = -1;
        float f1 = (float)rendererUpdateCount + partialTicks;
        bufferbuilder.setTranslation(-d0, -d1, -d2);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int k1 = k - i1; k1 <= k + i1; ++k1)
        {
            for (int l1 = i - i1; l1 <= i + i1; ++l1)
            {
                int i2 = (k1 - k + 16) * 32 + l1 - i + 16;
                double d3 = (double)rainXCoords[i2] * 0.5D;
                double d4 = (double)rainYCoords[i2] * 0.5D;
                blockpos$mutableblockpos.setPos(l1, 0, k1);
                Biome biome = world.getBiome(blockpos$mutableblockpos);

                if (biome.canRain() || biome.getEnableSnow())
                {
                    int j2 = world.getPrecipitationHeight(blockpos$mutableblockpos).getY();
                    int k2 = j - i1;
                    int l2 = j + i1;

                    if (k2 < j2)
                    {
                        k2 = j2;
                    }

                    if (l2 < j2)
                    {
                        l2 = j2;
                    }

                    int i3 = j2;

                    if (j2 < l)
                    {
                        i3 = l;
                    }

                    if (k2 != l2)
                    {
                        rand.setSeed((long)(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761));
                        blockpos$mutableblockpos.setPos(l1, k2, k1);
                        float f2 = biome.getTemperature(blockpos$mutableblockpos);
                        if (j1 != 1)
                        {
                            if (j1 >= 0)
                            {
                                tessellator.draw();
                            }

                            j1 = 1;
                            mc.getTextureManager().bindTexture(SNOW_TEXTURES);
                            bufferbuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                        }

                        double d8 = (double)(-((float)(rendererUpdateCount & 511) + partialTicks) / 512.0F);
                        double d9 = rand.nextDouble() + (double)f1 * 0.01D * (double)((float)rand.nextGaussian());
                        double d10 = rand.nextDouble() + (double)(f1 * (float)rand.nextGaussian()) * 0.001D;
                        double d11 = (double)((float)l1 + 0.5F) - entity.posX;
                        double d12 = (double)((float)k1 + 0.5F) - entity.posZ;
                        float f6 = MathHelper.sqrt(d11 * d11 + d12 * d12) / (float)i1;
                        float f5 = ((1.0F - f6 * f6) * 0.3F + 0.5F) * 1.0F;
                        blockpos$mutableblockpos.setPos(l1, i3, k1);
                        int i4 = (world.getCombinedLight(blockpos$mutableblockpos, 0) * 3 + 15728880) / 4;
                        int j4 = i4 >> 16 & 65535;
                        int k4 = i4 & 65535;
                        bufferbuilder.pos((double)l1 - d3 + 0.5D, (double)l2, (double)k1 - d4 + 0.5D).tex(0.0D + d9, (double)k2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5D, (double)l2, (double)k1 + d4 + 0.5D).tex(1.0D + d9, (double)k2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5D, (double)k2, (double)k1 + d4 + 0.5D).tex(1.0D + d9, (double)l2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 - d3 + 0.5D, (double)k2, (double)k1 - d4 + 0.5D).tex(0.0D + d9, (double)l2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();

                    }
                }
            }
        }

        if (j1 >= 0)
        {
            tessellator.draw();
        }

        bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);
        entityRenderer.disableLightmap();
    }


    public static void smokeAround(Entity entity, int count)
    {
        spawnParticlesAround(EnumParticleTypes.SMOKE_NORMAL, entity.world, entity.getPosition(), count);
    }

    public static void smoke(Entity entity, int count)
    {
        spawnParticles(EnumParticleTypes.SMOKE_NORMAL, entity.world, entity.getPosition(), count);
    }

    public static void spawnParticles(EnumParticleTypes type, World world, BlockPos pos, int count)
    {
        for (int i = 0; i < count; ++i)
        {
            double d0 = (double)((float)pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
            double d1 = (double)((float)pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
            double d2 = (double)((float)pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
            world.spawnParticle(type, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public static void spawnParticlesAround(EnumParticleTypes type, World world, BlockPos pos, int count)
    {
        for (int i = 0; i < count; ++i)
        {
            int i1 = rand.nextInt(2) * 2 - 1;
            int j1 = rand.nextInt(2) * 2 - 1;

            double x1 = (double)pos.getX() + 0.5D + 0.25D * (double)i1;
            double y1 = (double)((float)pos.getY() + rand.nextFloat());
            double z1 = (double)pos.getZ() + 0.5D + 0.25D * (double)j1;

            double vx = (double)(rand.nextFloat() * 1.0F * (float)i1);
            double vy = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            double vz = (double)(rand.nextFloat() * 1.0F * (float)j1);

            world.spawnParticle(type, x1, y1, z1, vx, vy, vz);
        }
    }

}
