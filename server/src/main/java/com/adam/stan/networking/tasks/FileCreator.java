package com.adam.stan.networking.tasks;

import com.adam.stan.files.FileInfo;
import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.InfoMessage;
import com.adam.stan.storage.UserRootDirectory;

public class FileCreator implements ServerTask {

    private UserRootDirectory rootUser;
    private FileInfo fileInfo;

    public FileCreator(UserRootDirectory rootUser, FileInfo fileInfo) {
        this.rootUser = rootUser;
        this.fileInfo = fileInfo;
    }

    @Override
    public ClientServerMessage doTask() {
        rootUser.createServerFile(fileInfo);
        return new InfoMessage(rootUser.getUser(), "Files were updated");
    }

}
