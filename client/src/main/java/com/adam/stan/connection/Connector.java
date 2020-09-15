package com.adam.stan.connection;

import java.util.List;

import com.adam.stan.files.Resource;
import com.adam.stan.security.User;
import com.adam.stan.storage.RootLocalDirectory;

public class Connector {

    private static Client client;

    private Connector() {
    }

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

    public static void sendFiles(List<Resource> resources) {
        synchronized (client) {
            client.sendFiles(resources);
        }
    }

    public static void downloadAllFiles(RootLocalDirectory root) {
        synchronized (client) {
            client.downloadAllFiles(root);
        }
    }

    public static void sendFile(Resource resource) {
        synchronized (client) {
            client.sendFile(resource);
        }
    }

    public static void deletedFile(Resource resource) {
        synchronized (client) {
            client.sendDeletedFile(resource);
        }
    }
}
