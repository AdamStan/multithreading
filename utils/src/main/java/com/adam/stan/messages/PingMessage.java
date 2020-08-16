package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class PingMessage implements ClientServerMessage {

    private static final long serialVersionUID = 5897528510679553058L;
    private User user;

    public PingMessage(User user) {
        this.user = user;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public User getUser() {
        return user;
    }

}
