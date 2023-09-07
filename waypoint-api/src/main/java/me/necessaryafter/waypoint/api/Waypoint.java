package me.necessaryafter.waypoint.api;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.credentials.AuthCredentials;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import redis.clients.jedis.JedisPool;

public interface Waypoint {

    boolean prepare(@NonNull AuthCredentials credentials);

    JedisPool getJedisPool();

    WaypointPacketService getPacketService();

    ServerRegistry getServerRegistry();

}
