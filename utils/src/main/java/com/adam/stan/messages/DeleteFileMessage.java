package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.files.FileInfo;
import com.adam.stan.files.Resource;
import com.adam.stan.security.User;

public class DeleteFileMessage extends AbstractMessage {

    private static final long serialVersionUID = -712714526332766488L;
    private final FileInfo info;

    public DeleteFileMessage(User user, Resource resource) {
        super(user);
        info = new FileInfo(resource.relativePath(), new byte[0], resource.isFile(), -1);
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(info);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.FILE_REMOVE;
    }

}
