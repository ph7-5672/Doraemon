package org.ph7.doraemon.command;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.ph7.doraemon.init.ModItems;
import org.ph7.doraemon.item.weapon.ItemAirGun;

public class CommandBang extends CommandBase
{
    @Override
    public String getName()
    {
        return "bang";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return I18n.format("commands.air_gun.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            ItemStack stack1 = player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack stack2 = player.getHeldItem(EnumHand.OFF_HAND);
            if (ModItems.AIR_GUN.equals(stack1.getItem()) || ModItems.AIR_GUN.equals(stack2.getItem()))
            {
                World world = player.world;
                if (!world.isRemote)
                {
                    ItemAirGun.EntityAirBullet bullet = new ItemAirGun.EntityAirBullet(world, player);
                    bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 1.0F);
                    world.spawnEntity(bullet);
                    world.playEvent(2000, player.getPosition().up(), 10);
                }
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
