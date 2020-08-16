package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;


public class InfoMessage extends AbstractMessage {

    private static final long serialVersionUID = -1727586400315105358L;
    private String message;

    public InfoMessage(User user, String messageText) {
        super(user);
        this.message = messageText;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(message);
    }

    @Override
    public String toString() {
        return "InfoMessage [message=" + message + "]";
    }

}
