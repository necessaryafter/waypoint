package me.necessaryafter.waypoint.api.packet;

import lombok.Getter;

@Getter
public abstract class WaypointPacket<T> {

    protected T object;

    public WaypointPacket(T object) {
        this.object = object;
    }

    public abstract void onSend(T data);

    public void onRead(T data) {}

}
