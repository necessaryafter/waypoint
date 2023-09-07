package me.necessaryafter.waypoint.api.auth;

public interface AuthCredentials {

    String getAddress();
    int getPort();

    String getUsername();
    String getPassword();

    default String getAddressFormatted() {
        return getAddress() + ":" + getPort();
    }

}
