package me.necessaryafter.waypoint.api;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.auth.AuthCredentials;
import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import redis.clients.jedis.JedisPool;

public interface Waypoint {

    boolean prepare(@NonNull AuthCredentials credentials);

    WaypointPacketService getPacketService();

    JedisPool getJedisPool();

    void setPacketService(@NonNull WaypointPacketService packetService);

}
