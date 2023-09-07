package me.necessaryafter.waypoint.test;

import me.necessaryafter.waypoint.api.Waypoint;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.server.Server;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import me.necessaryafter.waypoint.impl.WaypointBuilder;
import me.necessaryafter.waypoint.impl.credentials.AuthCredentialsBuilder;
import me.necessaryafter.waypoint.impl.server.ServerBuilder;
import me.necessaryafter.waypoint.test.data.Product;
import me.necessaryafter.waypoint.test.packet.ProductPacket;

public final class WaypointTest {

    public static void main(String[] args) {
        final Waypoint waypoint = WaypointBuilder.newBuilder()
                .setAuthCredentials(new AuthCredentialsBuilder()
                        .setAddress("127.0.0.1")
                        .setPort(6379)
                        .build())
                .setCurrentServer(ServerBuilder.newBuilder()
                        .setId("lobby-1")
                        .build())
                .build();

        final WaypointPacketService packetService = waypoint.getPacketService();
        final ServerRegistry serverRegistry = waypoint.getServerRegistry();

        final Product product = new Product("Camisa do Flamengo Versão 3", 1);
        packetService.publish(new ProductPacket(product));

        final Server server = serverRegistry.findServerById("lobby-2");
        if(server == null) return;

        packetService.send(server, new ProductPacket(product));
    }

}
