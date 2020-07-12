package org.ph7.doraemon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.ph7.doraemon.entity.EntityRandomDoor;

public class TransPacket implements IMessage
{

    private int entityId;
    private int x;
    private int y;
    private int z;

    public TransPacket()
    {
    }

    public TransPacket(int entityId, int x, int y, int z)
    {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
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
        final int entityId = buf.readInt();
        final int x = buf.readInt();
        final int y = buf.readInt();
        final int z = buf.readInt();
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.entityId);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public static class Handler implements IMessageHandler<TransPacket, IMessage>
    {

        @Override
        public IMessage onMessage(TransPacket message, MessageContext ctx)
        {
            final EntityPlayerMP player = ctx.getServerHandler().player;
            if (ctx.side.isServer() && player != null)
            {
                Entity entity = player.world.getEntityByID(message.entityId);
                if (entity instanceof EntityRandomDoor)
                {
                    ((EntityRandomDoor) entity).setTrans(message.x, message.y, message.z);
                }
            }

            return null;
        }
    }
}
