package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class LoginMessage extends AbstractMessage {

    private static final long serialVersionUID = -5664462723868125034L;

    public LoginMessage(User user) {
        super(user);
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(getUser());
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.LOGIN;
    }

}
