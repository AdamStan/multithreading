package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class Disconnect extends AbstractMessage {

    private static final long serialVersionUID = -7418141995955621652L;
    public static final String message = "DISCONNECT";

    public Disconnect(User user) {
        super(user);
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(message);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.DISCONNECT;
    }

}
