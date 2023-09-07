package me.necessaryafter.waypoint.impl.credentials;

import lombok.NonNull;
import me.necessaryafter.waypoint.api.credentials.AuthCredentials;

public final class AuthCredentialsBuilder {


    private String address, username, password;
    private int port;

    public AuthCredentialsBuilder() {
        throw new UnsupportedOperationException("Use newBuilder instead");
    }

    public static AuthCredentialsBuilder newBuilder() {
        return new AuthCredentialsBuilder();
    }

    public AuthCredentialsBuilder setAddress(@NonNull String address) {
        this.address = address;
        return this;
    }

    public AuthCredentialsBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public AuthCredentialsBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public AuthCredentialsBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public AuthCredentials build() {
        return new AuthCredentialsImpl(address, username, password, port);
    }

}
