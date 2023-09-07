package me.necessaryafter.waypoint.impl.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import me.necessaryafter.waypoint.api.Waypoint;
import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.server.Server;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import me.necessaryafter.waypoint.impl.util.NamedThreadFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class WaypointPacketServiceImpl implements WaypointPacketService {

    private final Gson gson;
    private final ExecutorService packetPublisherExecutor;
    private final ServerRegistry serverRegistry;
    private final Waypoint Waypoint;

    private final List<WaypointPacket> classes = new ArrayList<>();

    public WaypointPacketServiceImpl(@NonNull Gson gson, @NonNull ServerRegistry serverRegistry, Waypoint Waypoint) {
        this.serverRegistry = serverRegistry;
        this.Waypoint = Waypoint;
        this.packetPublisherExecutor = Executors.newSingleThreadExecutor(
                new NamedThreadFactory("waypoint-packet-publisher"));

        this.gson = gson;
    }

    public WaypointPacketServiceImpl(@NonNull ServerRegistry serverRegistry, Waypoint Waypoint) {
        this(new GsonBuilder().setPrettyPrinting().create(), serverRegistry, Waypoint);
    }

    @Override
    public <T extends WaypointPacket> void subscribe(WaypointPacket waypointPacket) {
        this.classes.add(waypointPacket);
    }

    @Override
    public WaypointPacket deserialize(String message) {
        return null;
    }

    /**
     * Publishes the given waypoint packet.
     *
     * @param  waypointPacket    the waypoint packet to be published
     */
    @Override
    public void publish(@NonNull WaypointPacket waypointPacket) {
        this.packetPublisherExecutor.execute(() -> {
            try (final Jedis jedis = Waypoint.getJedisPool().getResource()) {
                jedis.publish(String.format("waypoint-%s", waypointPacket.getClass().getName()), gson.toJson(waypointPacket));
            }
        });
    }

    @Override
    public void send(@NonNull Server server, @NonNull WaypointPacket waypointPacket) {
        this.packetPublisherExecutor.execute(() -> {
            try (final Jedis jedis = Waypoint.getJedisPool().getResource()) {
                jedis.publish(String.format("waypoint-%s-%s", server.getId(),
                        waypointPacket.getClass().getName()), gson.toJson(waypointPacket));
            }
        });
    }

    @Override
    public void shutdown() {
        this.packetPublisherExecutor.shutdownNow();
    }
}
