package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class LoginMessage implements ClientServerMessage {
    private static final long serialVersionUID = -5664462723868125034L;
    private User user;
    
    public LoginMessage(User user) {
        this.user = user;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(user);
    }

    @Override
    public User getUser() {
        return user;
    }

}
