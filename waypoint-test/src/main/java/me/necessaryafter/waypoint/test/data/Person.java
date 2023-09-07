package me.necessaryafter.waypoint.test.data;

import lombok.Data;

@Data(staticConstructor = "of")
public final class Person {

    private final String name;
    private final int age;

}
