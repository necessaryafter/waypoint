package me.necessaryafter.waypoint.impl;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.Waypoint;
import me.necessaryafter.waypoint.api.credentials.AuthCredentials;
import me.necessaryafter.waypoint.api.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WaypointBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaypointBuilder.class);

    private AuthCredentials credentials = null;
    private Server currentServer = null;

    public WaypointBuilder() {
        throw new UnsupportedOperationException("Use newBuilder instead");
    }

    public static WaypointBuilder newBuilder() {
        return new WaypointBuilder();
    }

    public WaypointBuilder setAuthCredentials(@NonNull AuthCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public WaypointBuilder setCurrentServer(@NonNull Server currentServer) {
        this.currentServer = currentServer;
        return this;
    }

    public Waypoint build() {
        final WaypointImpl waypoint = new WaypointImpl();
        if(waypoint.prepare(credentials)) {
            LOGGER.info("[Waypoint] Waypoint has been prepared!");
            return waypoint;
        }

        LOGGER.error("[Waypoint] Could not possible start waypoint.");
        return null;
    }

}
