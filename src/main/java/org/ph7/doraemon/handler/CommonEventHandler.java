package org.ph7.doraemon.handler;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandGameMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.ph7.doraemon.common.Attributes;
import org.ph7.doraemon.common.EntityUtil;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.init.ModBlocks;
import org.ph7.doraemon.init.ModEntities;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.init.ModPotions;
import org.ph7.doraemon.item.armor.ItemBambooDragonfly;
import org.ph7.doraemon.item.food.ItemMemoryBread;
import org.ph7.doraemon.item.misc.ItemSatanPassport;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonEventHandler
{
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        ModItems.ITEMS.forEach(i ->
        {
            event.getRegistry().register(i);
        });
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        ModBlocks.BLOCKS.forEach(b ->
        {
            event.getRegistry().register(b);
        });
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        ModEntities.init();
        ModEntities.ENTITY_ENTRIES.forEach(e ->
        {
            event.getRegistry().register(e);
        });
    }

    @SubscribeEvent
    public void registerPotions(RegistryEvent.Register<Potion> event)
    {
        ModPotions.POTIONS.forEach(p ->
        {
            event.getRegistry().register(p);
        });
    }

    @SubscribeEvent
    public void attachAttributes(EntityEvent.EntityConstructing event)
    {
        if (event.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase)event.getEntity();
            AbstractAttributeMap map = entity.getAttributeMap();
            map.registerAttribute(Attributes.ENTITY_WIDTH_SCALE);
            map.registerAttribute(Attributes.ENTITY_HEIGHT_SCALE);
            map.registerAttribute(Attributes.ENTITY_WIDTH);
            map.registerAttribute(Attributes.ENTITY_HEIGHT);
        }
    }

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        float widthScale = EntityUtil.getWidthScale(entity);
        float heightScale = EntityUtil.getHeightScale(entity);

        float defaultWidth = EntityUtil.getDefaultWidth(entity);
        float defaultHeight = EntityUtil.getDefaultHeight(entity);

        if (widthScale != 1.0F || heightScale != 1.0F)
        {
            if (defaultWidth == entity.width || defaultHeight == entity.height)
            {
                EntityUtil.setScale(entity, 1.0F, 1.0F);
                EntityUtil.scale(entity, widthScale, heightScale);
            }
        }
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event)
    {
        //模型大小用
        if (event.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            EntityUtil.setDefaultSize(entity, entity.width, entity.height);
        }
    }


    @SubscribeEvent
    public void entityInit(EntityEvent.EntityConstructing event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            EntityDataManager dataManager = player.getDataManager();
            //记忆面包用
            dataManager.register(ItemMemoryBread.MEMORY, new NBTTagCompound());
            //todo readNBT

            //恶魔护照用
            dataManager.register(ItemSatanPassport.GAME_TYPE, GameType.CREATIVE.getID());
        }
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) return;
        GameType currentGameType = mc.playerController.getCurrentGameType();
        EntityDataManager dataManager = player.getDataManager();

        //竹蜻蜓 Start
        ItemStack headStack = player.inventory.armorInventory.get(3);
        if (!currentGameType.isCreative())
        {
            player.capabilities.allowFlying = headStack.getItem() instanceof ItemBambooDragonfly;
        }
        //End

        //恶魔护照 Start
        if (ItemUtil.isPlayerHolding(player, ModItems.SATAN_PASSPORT))
        {
            //手持护照时设置为创造模式
            player.setGameType(GameType.CREATIVE);
            //记录原本模式
            if (!currentGameType.isCreative())
            {
                dataManager.set(ItemSatanPassport.GAME_TYPE, currentGameType.getID());
            }
        }
        else
        {
            //还原到原本的模式
            Integer typeId = dataManager.get(ItemSatanPassport.GAME_TYPE);
            GameType gameType = GameType.getByID(typeId);
            if (!gameType.isCreative() && currentGameType != gameType)
            {
                player.setGameType(gameType);
                //还原后清空
                dataManager.set(ItemSatanPassport.GAME_TYPE, GameType.CREATIVE.getID());
            }
        }
        //End

    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
    }

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
    }





    
}
