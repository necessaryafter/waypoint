package me.necessaryafter.waypoint.api.credentials;

public interface AuthCredentials {

    String getAddress();
    int getPort();

    String getUsername();
    String getPassword();

    default String getAddressFormatted() {
        return getAddress() + ":" + getPort();
    }

}
