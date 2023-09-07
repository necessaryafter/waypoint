package me.necessaryafter.waypoint.api.registry;

import me.necessaryafter.waypoint.api.server.Server;

import java.util.Collection;

public interface ServerRegistry {

    void registerServer(Server server);

    Server currentServer();

    Collection<Server> getServers();

}
