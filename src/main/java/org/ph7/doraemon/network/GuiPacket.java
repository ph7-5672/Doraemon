package org.ph7.doraemon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.ph7.doraemon.common.GuiManager;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.gui.GuiRandomDoor;

public class GuiPacket implements IMessage
{

	private int x;
	private int y;
	private int z;

	public GuiPacket()
	{
	}

	public GuiPacket(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
	}
	
	public static class Handler implements IMessageHandler<GuiPacket, IMessage>
    {

    	@Override
        public IMessage onMessage(GuiPacket message, MessageContext ctx)
        {
    		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().player;
    		
    		if(sendingPlayer instanceof EntityPlayer)
    		{
    			EntityPlayer player = (EntityPlayer)sendingPlayer;
    			
    			if(ctx.side.isServer() && player != null)
                { 
					GuiManager.openGui(GuiRandomDoor.class, player, player.world, message.x, message.y, message.z);
                }
    		}
            return null;
        }
    }
}