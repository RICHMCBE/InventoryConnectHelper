package romo.inventoryconnecthelper.protocol;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import io.netty.buffer.ByteBuf;
import romo.inventoryconnecthelper.InventoryConnectHelper;


import java.util.Objects;

public class InventorySavePacket extends StarGatePacket {

    private String clientName;
    private int status;
    private long xuid;

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        PacketHelper.writeString(byteBuf, this.clientName);
        PacketHelper.writeInt(byteBuf, this.status);
        PacketHelper.writeLong(byteBuf, this.xuid);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        this.clientName = PacketHelper.readString(byteBuf);
        this.status = PacketHelper.readInt(byteBuf);
        this.xuid = PacketHelper.readLong(byteBuf);
    }

    @Override
    public byte getPacketId() {
        return 14;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getXuid() {
        return xuid;
    }

    public void setXuid(long xuid) {
        this.xuid = xuid;
    }

    @Override
    public boolean handle(StarGatePacketHandler handler) {
        InventoryConnectHelper.getInstance().getInventoryConnectServerSessions().forEach((key, session) -> {
            if(!Objects.equals(session.getSessionName(), this.clientName)){
                session.sendPacket(this);
            }
        });
        return true;
    }
}
