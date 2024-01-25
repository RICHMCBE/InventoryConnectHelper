package romo.inventoryconnecthelper;

import alemiz.stargate.StarGate;
import dev.waterdog.waterdogpe.plugin.Plugin;
import romo.inventoryconnecthelper.protocol.InventorySavePacket;

public class InventoryConnectHelper extends Plugin {
    @Override
    public void onEnable(){
        StarGate.getInstance().getServer().getProtocolCodec().registerPacket((byte) 14, InventorySavePacket.class);
    }
}