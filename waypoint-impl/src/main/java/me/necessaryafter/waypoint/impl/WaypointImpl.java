package me.necessaryafter.waypoint.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.necessaryafter.waypoint.api.credentials.AuthCredentials;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Setter
public final class WaypointImpl implements me.necessaryafter.waypoint.api.Waypoint {

    @Getter(AccessLevel.PRIVATE)
    private static final Logger LOGGER = LoggerFactory.getLogger(WaypointImpl.class);

    private JedisPool jedisPool;
    private WaypointPacketService waypointPacketService;
    private ServerRegistry serverRegistry;

    @Override
    public boolean prepare(@NonNull AuthCredentials credentials) {
        if(serverRegistry == null || serverRegistry.currentServer() == null)
            return false;

        final DefaultJedisClientConfig.Builder builder = DefaultJedisClientConfig.builder();
        if(credentials.getUsername() != null) builder.user(credentials.getUsername());
        if(credentials.getPassword() != null) builder.password(credentials.getPassword());

        this.jedisPool = new JedisPool(HostAndPort.from(credentials.getAddressFormatted()), builder.build());
        this.jedisPool.setMaxTotal(8);

        LOGGER.debug("[Waypoint] [Redis] Trying pinging redis...");
        try (final Jedis jedis = jedisPool.getResource()) {
            LOGGER.debug("[Waypoint] [Redis] Result: " + jedis.ping());

            return true;
        } catch (Exception exception) {
            LOGGER.error("[Waypoint] [Redis] Cannot be ping redis, waypoint disabled.");
            return false;
        }
    }

    @Override
    public WaypointPacketService getPacketService() {
        return waypointPacketService;
    }

    @Override
    public ServerRegistry getServerRegistry() {
        return serverRegistry;
    }

    @Override
    public JedisPool getJedisPool() {
        return jedisPool;
    }


}
