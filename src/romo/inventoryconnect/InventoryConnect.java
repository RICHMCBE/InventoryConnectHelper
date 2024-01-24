package romo.inventoryconnect;

import alemiz.stargate.StarGate;
import dev.waterdog.waterdogpe.plugin.Plugin;
import romo.inventoryconnect.protocol.InventorySavePacket;

public class InventoryConnect extends Plugin {
    @Override
    public void onEnable(){
        StarGate.getInstance().getServer().getProtocolCodec().registerPacket((byte) 14, InventorySavePacket.class);
    }
}