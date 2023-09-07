package me.necessaryafter.waypoint.impl.server;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.registry.ServerRegistry;
import me.necessaryafter.waypoint.api.server.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class ServerRegistryImpl implements ServerRegistry {

    private final Map<String, Server> serverMap;
    private final Server currentServer;

    public ServerRegistryImpl(Server currentServer) {
        this.serverMap = new HashMap<>();
        this.currentServer = currentServer;
    }

    @Override
    public void registerServer(Server server) {
        this.serverMap.put(server.getId(), server);
    }

    @Override
    public Server findServerById(@NonNull String serverId) {
        return this.serverMap.get(serverId);
    }

    @Override
    public Server currentServer() {
        return this.currentServer;
    }

    @Override
    public Collection<Server> getServers() {
        return new ArrayList<>(this.serverMap.values());
    }
}
