package me.necessaryafter.waypoint.impl.server;

import lombok.RequiredArgsConstructor;
import me.necessaryafter.waypoint.api.server.Server;

@RequiredArgsConstructor
public final class ServerImpl implements Server {

    private final String serverId;

    @Override
    public String getId() {
        return serverId;
    }
}
