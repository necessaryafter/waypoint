package me.necessaryafter.waypoint.impl.credentials;

import lombok.RequiredArgsConstructor;
import me.necessaryafter.waypoint.api.auth.AuthCredentials;

@RequiredArgsConstructor
public final class AuthCredentialsImpl implements AuthCredentials {

    private final String address, username, password;
    private final int port;


    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
