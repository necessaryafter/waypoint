package me.necessaryafter.waypoint.api.packet;

/**
 * Class responsible for packets receive and send handler.
 */
public abstract class WaypointPacket<V> {

    /**
     * Functions that are called when the packet is sent
     */
    public void onSend() {}

    /**
     * Functions that are called when the packet is received
     */
    public abstract void onReceive();

}
