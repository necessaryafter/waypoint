package me.necessaryafter.waypoint.impl.server;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.server.Server;

public final class ServerBuilder {

    private String serverId;

    public ServerBuilder() {
        throw new UnsupportedOperationException("Use newBuilder instead");
    }

    public static ServerBuilder newBuilder() {
        return new ServerBuilder();
    }

    public ServerBuilder setId(@NonNull String serverId) {
        this.serverId = serverId;
        return this;
    }

    public Server build() {
        return new ServerImpl(serverId);
    }

}
