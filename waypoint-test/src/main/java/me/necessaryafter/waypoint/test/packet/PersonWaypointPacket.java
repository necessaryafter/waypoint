package me.necessaryafter.waypoint.test.packet;

import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.test.data.Person;

public final class PersonWaypointPacket extends WaypointPacket<Person> {

    public PersonWaypointPacket(Person person) {
        super(person);
    }

    @Override
    public void onSend(Person data) {

    }

}
