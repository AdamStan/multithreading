package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class PingMessage extends AbstractMessage {

    private static final long serialVersionUID = 5897528510679553058L;

    public PingMessage(User user) {
        super(user);
    }

    @Override
    public Optional<?> getValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.PING;
    }

}
