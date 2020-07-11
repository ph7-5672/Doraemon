package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.ph7.doraemon.block.BlockRandomDoor;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ItemStackRenderer;
import org.ph7.doraemon.render.tile.TileEntityRandomDoorRenderer;
import org.ph7.doraemon.tile.TileEntityRandomDoor;
import java.util.List;

public class ModBlocks
{
    public static List<Block> BLOCKS = Lists.newArrayList();
    public static List<Item> ITEM_BLOCKS = Lists.newArrayList();

    public static BlockRandomDoor RANDOM_DOOR = new BlockRandomDoor();

    static
    {
        register("random_door", RANDOM_DOOR, TileEntityRandomDoor.class, new TileEntityRandomDoorRenderer(), false);
    }



    public static void register(String name, Block block)
    {
        register(name, block, true);
    }

    public static void register(String name, Block block, boolean withItemBlock)
    {
        register(name, block, null, null, withItemBlock);
    }

    public static void register(String name, Block block, Class<? extends TileEntity> entityClass, TileEntitySpecialRenderer renderer)
    {
        register(name, block, entityClass, renderer, true);
    }

    public static void register(String name, Block block, Class<? extends TileEntity> entityClass, TileEntitySpecialRenderer renderer, boolean withItemBlock)
    {
        block.setRegistryName(Reference.MOD_ID, name);
        block.setUnlocalizedName(name);
        block.setCreativeTab(Reference.CREATIVE_TAB);
        BLOCKS.add(block);

        if (withItemBlock)
        {
            Item item = new ItemBlock(block).setRegistryName(Reference.MOD_ID, name);
            item.setUnlocalizedName(name);
            item.setTileEntityItemStackRenderer(ItemStackRenderer.instance);
            ITEM_BLOCKS.add(item);
        }

        if (entityClass != null && renderer != null)
        {
            GameRegistry.registerTileEntity(entityClass, block.getRegistryName());
            ClientRegistry.bindTileEntitySpecialRenderer(entityClass, renderer);
        }
    }
}
