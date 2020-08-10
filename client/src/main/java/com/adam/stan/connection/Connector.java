package com.adam.stan.connection;

import java.util.ArrayList;
import java.util.List;

import com.adam.stan.storage.files.Resource;

public class Connector {

    private final String url;
    private static final List<Resource> resources = new ArrayList<>();

    public Connector(String address) {
        url = address;
    }

    public void connect() throws ConnectException {
        try {
            // TODO: make connection
        } catch (Exception e) {
            throw new ConnectException(e);
        }
    }

    public List<Resource> getUserRootItems() {
        return resources;
    }
}
