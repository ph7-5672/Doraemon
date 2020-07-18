package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.ph7.doraemon.common.Reference;

import java.util.List;

public class ModBlocks
{
    public static List<Block> BLOCKS = Lists.newArrayList();


    static
    {
    }


    public static void register(String name, Block block)
    {
        register(name, block, null, null);
    }

    public static void register(String name, Block block, Class<? extends TileEntity> entityClass, TileEntitySpecialRenderer renderer)
    {
        block.setRegistryName(Reference.MOD_ID, name);
        block.setUnlocalizedName(name);
        block.setCreativeTab(Reference.CREATIVE_TAB);
        BLOCKS.add(block);

        if (entityClass != null && renderer != null)
        {
            GameRegistry.registerTileEntity(entityClass, block.getRegistryName());
            ClientRegistry.bindTileEntitySpecialRenderer(entityClass, renderer);
        }
    }
}
