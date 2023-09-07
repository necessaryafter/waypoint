package me.necessaryafter.waypoint.impl.pubsub;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.server.Server;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import redis.clients.jedis.JedisPubSub;

@RequiredArgsConstructor
public final class WaypointPubSub extends JedisPubSub {

    private final WaypointPacketService packetService;
    private final ServerRegistry serverRegistry;

    @SneakyThrows
    @Override
    public void onMessage(String channelRaw, String message) {
        if (!channelRaw.startsWith("waypoint")) {
            return;
        }

        final String[] channelArray = channelRaw.split("-");
        if(channelArray.length >= 2) {
            final Server currentServer = serverRegistry.currentServer();
            if (!channelArray[1].equals(currentServer.getId())) {
                return;
            }
        }

        final WaypointPacket waypointPacket = packetService.deserialize(message);
        waypointPacket.onReceive();
    }
}
