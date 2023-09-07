package me.necessaryafter.waypoint.api.registry;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.server.Server;

import java.util.Collection;

public interface ServerRegistry {

    void registerServer(@NonNull Server server);

    Server findServerById(@NonNull String serverId);

    Server currentServer();

    Collection<Server> getServers();

}
