package com.adam.stan.messages;

import java.util.List;
import java.util.Optional;

import com.adam.stan.files.FileInfo;
import com.adam.stan.security.User;

public class FilesMessage extends AbstractMessage implements ClientServerMessage {

    private static final long serialVersionUID = 4348609082978767688L;
    private List<FileInfo> infos;

    public FilesMessage(User user, List<FileInfo> infos) {
        super(user);
        this.infos = infos;
    }

    @Override
    public Optional<?> getValue() {
        return Optional.of(infos);
    }

    @Override
    public UseCase getOperationType() {
        return UseCase.FILE_REFRESH;
    }

}
