package romo.inventoryconnecthelper.protocol;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import io.netty.buffer.ByteBuf;
import romo.inventoryconnecthelper.InventoryConnectHelper;

public class InventoryConnectSessionConnectPacket extends StarGatePacket {

    private String sessionName;

    @Override
    public byte getPacketId() {
        return 15;
    }

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        //NOTHING
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        this.sessionName = PacketHelper.readString(byteBuf);
    }

    @Override
    public boolean handle(StarGatePacketHandler handler) {
        InventoryConnectHelper.getInstance().onInventoryConnectSessionConnect(this.sessionName);
        return true;
    }

    public String getSessionName() {
        return sessionName;
    }
}
