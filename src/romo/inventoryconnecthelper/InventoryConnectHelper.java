package romo.inventoryconnecthelper;

import alemiz.stargate.StarGate;
import alemiz.stargate.events.ClientDisconnectedEvent;
import alemiz.stargate.server.ServerSession;
import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import romo.inventoryconnecthelper.protocol.InventoryConnectSessionConnectPacket;
import romo.inventoryconnecthelper.protocol.InventorySavePacket;

import java.util.HashMap;

public class InventoryConnectHelper extends Plugin {

    private static InventoryConnectHelper instance;

    public static InventoryConnectHelper getInstance() {
        return instance;
    }

    private final HashMap<String, ServerSession> inventoryConnectServerSessions = new HashMap<>();

    @Override
    public void onEnable(){
        instance = this;
        StarGate.getInstance().getServer().getProtocolCodec().registerPacket((byte) 26, InventorySavePacket.class);
        StarGate.getInstance().getServer().getProtocolCodec().registerPacket((byte) 27, InventoryConnectSessionConnectPacket.class);
        ProxyServer.getInstance().getEventManager().subscribe(ClientDisconnectedEvent.class, this::onClientDisconnected);
    }



    public void onClientDisconnected(ClientDisconnectedEvent event){
        inventoryConnectServerSessions.remove(event.getSession().getSessionName());
    }

    public HashMap<String, ServerSession> getInventoryConnectServerSessions() {
        return inventoryConnectServerSessions;
    }

    public void onInventoryConnectSessionConnect(String sessionName){
        ServerSession session = StarGate.getInstance().getSession(sessionName);
        if(session == null){
            return;
        }
        inventoryConnectServerSessions.put(sessionName, session);
    }
}