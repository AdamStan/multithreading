package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;

public class FileMessage extends AbstractMessage {

    private static final long serialVersionUID = -1200286505879748658L;

    public FileMessage(User user) {
        super(user);
    }

    @Override
    public Optional<?> getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UseCase getOperationType() {
        // TODO Auto-generated method stub
        return null;
    }

}
