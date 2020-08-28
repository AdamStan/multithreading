package com.adam.stan.messages;

import java.util.List;
import java.util.Optional;

import com.adam.stan.files.Resource;
import com.adam.stan.security.User;

public class FileMessage extends AbstractMessage {

    private static final long serialVersionUID = -1200286505879748658L;
    private List<Resource> resources;

    public FileMessage(User user, List<Resource> resources) {
        super(user);
        this.resources = resources;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(resources);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.FILE_REFRESH;
    }

}
