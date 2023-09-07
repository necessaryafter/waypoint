package me.necessaryafter.waypoint.api.service;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.api.server.Server;

public interface WaypointPacketService {

    <T extends WaypointPacket> void subscribe(WaypointPacket waypointPacket);

    WaypointPacket deserialize(String message);

    void publish(@NonNull WaypointPacket waypointPacket);

    void send(@NonNull Server server, @NonNull WaypointPacket waypointPacket);

    void shutdown();

}
