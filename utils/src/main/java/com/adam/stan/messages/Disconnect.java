package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class Disconnect implements ClientServerMessage {

    private static final long serialVersionUID = -7418141995955621652L;
    public static final String message = "DISCONNECT";

    private User user;

    public Disconnect(User user) {
        this.user = user;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(message);
    }

    @Override
    public User getUser() {
        return user;
    }

}
