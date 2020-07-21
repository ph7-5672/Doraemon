package org.ph7.doraemon.entity;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
import org.ph7.doraemon.init.ModItems;
import javax.annotation.Nullable;

public class EntityShop extends EntityBase implements IMerchant
{
    @Nullable
    private EntityPlayer buyingPlayer;
    @Nullable
    private MerchantRecipeList buyingList;

    public EntityShop(World worldIn)
    {
        super(worldIn);
        this.buyingList = new MerchantRecipeList();
        ModItems.ITEMS.forEach(i ->
        {
            if (!ModItems.SHOP.equals(i))
            {
                this.buyingList.add(new MerchantRecipe(new ItemStack(Items.EMERALD), new ItemStack(i)));
            }
        });
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
        return new ItemStack(ModItems.SHOP);
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
}
