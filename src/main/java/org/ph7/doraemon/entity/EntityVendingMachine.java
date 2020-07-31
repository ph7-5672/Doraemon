package org.ph7.doraemon.entity;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.ItemUtil;
import org.ph7.doraemon.init.ModItems;
import javax.annotation.Nullable;

public class EntityVendingMachine extends EntityBase implements IMerchant
{
    @Nullable
    private EntityPlayer buyingPlayer;
    @Nullable
    private MerchantRecipeList buyingList;

    public EntityVendingMachine(World worldIn)
    {
        super(worldIn);
        this.setSize(1.0F, 2.0F);
        this.initBuyingList();
    }

    protected void initBuyingList()
    {
        this.buyingList = new MerchantRecipeList();

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(1), new ItemStack(ModItems.DIMENSIONAL_POCKET)));
        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(10), new ItemStack(ModItems.BAMBOO_DRAGONFLY)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(1), new ItemStack(ModItems.FISHING_ROD_AUTO)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(5), new ItemStack(ModItems.DICTATOR_BUTTON)));
        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(2), new ItemStack(ModItems.AIR_GUN)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(20), new ItemStack(ModItems.RANDOM_DOOR)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(1), new ItemStack(ModItems.SUNNY_DOLL)));
        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(2), new ItemStack(ModItems.WEATHER_BOX)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(1), new ItemStack(ModItems.LOVE_ARROW, 64, 0)));
        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(2), new ItemStack(ModItems.SCARECROW)));

        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(2), new ItemStack(ModItems.LIGHT, 1, 0)));
        this.buyingList.add(new MerchantRecipe(ItemUtil.emerald(2), new ItemStack(ModItems.LIGHT, 1, 1)));

    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (!this.world.isRemote)
        {
            this.setCustomer(player);
            player.displayVillagerTradeGui(this);
        }
        return true;
    }

    @Override
    protected ItemStack getDropItem()
    {
        return new ItemStack(ModItems.VENDING_MACHINE);
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    @Override
    public void setCustomer(@Nullable EntityPlayer player)
    {
        this.buyingPlayer = player;
    }

    @Nullable
    @Override
    public EntityPlayer getCustomer()
    {
        return this.buyingPlayer;
    }

    @Nullable
    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player)
    {
        return ForgeEventFactory.listTradeOffers(this, player, this.buyingList);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setRecipes(@Nullable MerchantRecipeList recipeList) {

    }

    @Override
    public void useRecipe(MerchantRecipe recipe)
    {

    }

    @Override
    public void verifySellingItem(ItemStack stack)
    {

    }

    @Override
    public World getWorld()
    {
        return this.world;
    }

    @Override
    public BlockPos getPos()
    {
        return new BlockPos(this);
    }

    @Override
    public String getName()
    {
        return net.minecraft.client.resources.I18n.format("item.vending_machine.name");
    }
}
