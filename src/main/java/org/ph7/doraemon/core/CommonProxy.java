package org.ph7.doraemon.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.ph7.doraemon.command.CommandBang;
import org.ph7.doraemon.common.ISubItem;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.handler.ClientEventHandler;
import org.ph7.doraemon.handler.CommonEventHandler;
import org.ph7.doraemon.handler.GuiHandler;
import org.ph7.doraemon.network.GuiPacket;
import org.ph7.doraemon.network.TransPacket;


public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        registerEventHandler();
        registerGuiHandler();
        registerPackets();
        registerCapabilities();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public void registerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    }

    public void registerGuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Doraemon.instance, new GuiHandler());
    }

    public void registerPackets()
    {
        Doraemon.NETWORK.registerMessage(GuiPacket.Handler.class, GuiPacket.class, 0, Side.SERVER);
        Doraemon.NETWORK.registerMessage(TransPacket.Handler.class, TransPacket.class, 0, Side.SERVER);
    }

    public void registerCapabilities()
    {
        //CapabilityManager.INSTANCE.register(IEntitySizeCap.class, new EntitySizeStorage(), EntitySizeDefaultCap::new);
    }

    public void setModelResource(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    public void setModelResource(Item item)
    {
        NonNullList<ItemStack> list = NonNullList.create();
        item.getSubItems(item.getCreativeTab(), list);

        for (int i = 0; i < list.size(); i++)
        {
            ItemStack stack = list.get(i);
            String replace = item instanceof ItemBlock ? "tile." : "item.";
            String name = item.getUnlocalizedName();
            if (item instanceof ISubItem)
            {
                name += "_" + ((ISubItem) item).getSubNames().get(i);
            }
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name.replace(replace, ""));
            ModelLoader.setCustomModelResourceLocation(item, stack.getMetadata(), new ModelResourceLocation(location, "inventory"));
        }
    }

    public void registerCmd(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new CommandBang());
    }
}
