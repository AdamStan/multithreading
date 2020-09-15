package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.security.User;


public class RequestAllFiles extends AbstractMessage implements ClientServerMessage {

    /** auto generated */
    private static final long serialVersionUID = -1081140507530443049L;

    public RequestAllFiles(User user) {
        super(user);
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(getUser());
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.FILES_DOWNLOAD;
    }

}
