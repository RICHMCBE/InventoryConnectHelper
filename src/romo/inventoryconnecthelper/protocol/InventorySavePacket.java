package romo.inventoryconnecthelper.protocol;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import io.netty.buffer.ByteBuf;

public class InventorySavePacket extends StarGatePacket {

    private int status;
    private long xuid;

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        PacketHelper.writeInt(byteBuf, this.status);
        PacketHelper.writeLong(byteBuf, this.xuid);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        this.status = PacketHelper.readInt(byteBuf);
        this.xuid = PacketHelper.readLong(byteBuf);
    }

    @Override
    public byte getPacketId() {
        return 14;
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
        System.out.println(this.status);
        System.out.println(this.xuid);
        return true;
    }
}
