package com.adam.stan.messages;

import com.adam.stan.security.User;

public abstract class AbstractMessage implements ClientServerMessage {

    private static final long serialVersionUID = 1L;
    private final User user;

    public AbstractMessage(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

}
