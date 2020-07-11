package org.ph7.doraemon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.ph7.doraemon.core.Doraemon;

public class GuiPacket implements IMessage
{

	private byte id;

	public GuiPacket()
	{
	}

	public GuiPacket(byte id)
	{
		this.id = id;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.id = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeByte(this.id);
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
                	int posX = player.getPosition().getX();
                	int posY = player.getPosition().getY();
                	int posZ = player.getPosition().getZ();
                	World world = player.world;

					final WorldServer playerWorldServer = sendingPlayer.getServerWorld();

					playerWorldServer.addScheduledTask(new Runnable()
					{
						@Override
						public void run()
						{
							player.openGui(Doraemon.instance, message.id, world, posX, posY, posZ);
						}
					});
                }
    		}
            return null;
        }
    }
}