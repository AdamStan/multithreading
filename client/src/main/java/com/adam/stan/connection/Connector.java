package com.adam.stan.connection;

public class Connector {

    private final String url;

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

    public void getUserRootItems() {
        // TODO Auto-generated method stub
    }
}
