package com.adam.stan.messages;

import java.util.Optional;

import com.adam.stan.files.FileInfo;
import com.adam.stan.files.Resource;
import com.adam.stan.security.User;

public class FileMessage extends AbstractMessage {

    private static final long serialVersionUID = -1200286505879748658L;
    private FileInfo info;

    public FileMessage(User user, Resource resource, byte[] content) {
        super(user);
        info = new FileInfo(resource.relativePath(), content, resource.isFile(), resource.getLastModifiedDate());
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(info);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.FILE_REFRESH;
    }

}
