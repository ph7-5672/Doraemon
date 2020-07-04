package org.ph7.doraemon.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.ph7.doraemon.block.BlockRandomDoor;
import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<Item> ITEM_BLOCKS = new ArrayList<>();

    public static BlockRandomDoor RANDOM_DOOR = new BlockRandomDoor();

    public static void init()
    {
        //register(RANDOM_DOOR);
    }

    public static void register(Block block)
    {
        register(block, null, null);
    }

    public static void register(Block block, Class<? extends TileEntity> entityClass, TileEntitySpecialRenderer renderer)
    {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(block),
                0,
                new ModelResourceLocation(
                        block.getRegistryName(), "inventory"
                )
        );
        ForgeRegistries.BLOCKS.register(block);
        BLOCKS.add(block);

        Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        ModelLoader.setCustomModelResourceLocation(
                item,
                0,
                new ModelResourceLocation(
                        block.getRegistryName(), "inventory"
                )
        );
        ForgeRegistries.ITEMS.register(item);
        ITEM_BLOCKS.add(item);

        if (entityClass != null && renderer != null)
        {
            GameRegistry.registerTileEntity(entityClass, block.getRegistryName());
            ClientRegistry.bindTileEntitySpecialRenderer(entityClass, renderer);
        }
    }

}
