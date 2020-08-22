package com.adam.stan.connection;

import com.adam.stan.security.User;

public class Connector {

    private static Client client;

    public static void connect(String username) {
        User user = new User(username);
        client = new Client(user);
        client.init();
        client.sendInitialMessage();
    }

    public static void disconnect() {
        if (client != null && client.isConnected()) {
            client.sendDisconnect();
        }
    }

    public static void sendInfo(String message) {
        client.sendInfo(message);
    }

    public static void sendFile() {
        client.sendFile();
    }

    private Connector() {
    }
}
