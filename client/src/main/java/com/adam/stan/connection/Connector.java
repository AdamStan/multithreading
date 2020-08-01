package com.adam.stan.connection;

public class Connector {

    private final String url;

    public Connector(String address) {
        url = address;
    }

    public void connect() throws ConnectException {
        // TODO: make connection
    }
}
