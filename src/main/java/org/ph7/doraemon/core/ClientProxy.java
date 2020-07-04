package org.ph7.doraemon.core;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.lwjgl.input.Keyboard;
import org.ph7.doraemon.common.IHasGui;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy
{
    public static KeyBinding openDimensionalPocket = new KeyBinding(Reference.KEYS_INVENTORY, Keyboard.KEY_B, Reference.KEYS_CATEGORY);
    public static final List<IHasGui> GUI_LIST = new ArrayList<>();

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        registerKeyBindings();
        addGuiList();
    }

    private void addGuiList()
    {
        for (Item item : ModItems.ITEMS)
        {
            if (item instanceof IHasGui)
            {
                GUI_LIST.add((IHasGui) item);
            }
        }
        for (Block block : ModBlocks.BLOCKS)
        {
            if (block instanceof IHasGui)
            {
                GUI_LIST.add((IHasGui) block);
            }
        }
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(openDimensionalPocket);
    }
}
