package com.adam.stan.connection;

import java.util.List;

import com.adam.stan.files.Resource;
import com.adam.stan.security.User;

public class Connector {

    private static Client client;

    public static void connect(String username) {
        User user = new User(username);
        client = new Client(user);
        synchronized (client) {
            client.init();
            client.sendInitialMessage();
        }
    }

    public static void disconnect() {
        if (client != null && client.isConnected()) {
            synchronized (client) {
                client.sendDisconnect();
            }
        }
    }

    public static void sendInfo(String message) {
        synchronized (client) {
            client.sendInfo(message);
        }
    }

    public static void sendFile(List<Resource> resources) {
        synchronized (client) {
            client.sendFile(resources);
        }
    }

    private Connector() {
    }
}
