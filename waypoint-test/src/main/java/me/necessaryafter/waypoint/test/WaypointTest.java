package me.necessaryafter.waypoint.test;

import me.necessaryafter.waypoint.api.Waypoint;
import me.necessaryafter.waypoint.api.service.WaypointPacketService;
import me.necessaryafter.waypoint.impl.WaypointImpl;
import me.necessaryafter.waypoint.impl.credentials.AuthCredentialsImpl;
import me.necessaryafter.waypoint.test.data.Person;
import me.necessaryafter.waypoint.test.packet.PersonWaypointPacket;

public final class WaypointTest {

    public static void main(String[] args) {
        final Waypoint waypoint = new WaypointImpl();
        waypoint.prepare(new AuthCredentialsImpl("127.0.01", "user", "123", 6379));

        final Person person = Person.of("Bruno", 17);

        final WaypointPacketService packetService = waypoint.getPacketService();
        packetService.publish(new PersonWaypointPacket(person));
    }

}
