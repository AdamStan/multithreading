package com.adam.stan.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import com.adam.stan.files.FileInfo;
import com.adam.stan.security.User;

public class UserRootDirectory {

    private static final Logger LOGGER = Logger.getLogger(UserRootDirectory.class.getName());
    private final File userDirectory;
    private final User user;

    public UserRootDirectory(User user) {
        this.user = user;
        userDirectory = new File(RootDirectory.NAME + File.separatorChar + user);
        userDirectory.mkdirs();
    }

    public File getRootFile() {
        return userDirectory;
    }

    public void createServerFile(FileInfo file) {
        String relativePath = file.getRelativePath();
        String globalPathStart = userDirectory.getAbsolutePath();
        File fileOrDirectory = new File(globalPathStart + relativePath);

        if (file.isFile()) {
            try {
                if (!fileOrDirectory.exists()) {
                    LOGGER.info("file: " + file.getRelativePath() + " not exists, will be created.");
                    fileOrDirectory.createNewFile();
                } else {
                    //compare
                    long time = fileOrDirectory.lastModified();
                    if (time == file.getModificationDate()) {
                        LOGGER.info("file: " + file.getRelativePath() + " exists and was not changed");
                        return;
                    }
                }
                Files.write(fileOrDirectory.toPath(), file.getContent(), StandardOpenOption.WRITE);
                // set modification date
                fileOrDirectory.setLastModified(file.getModificationDate());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileOrDirectory.mkdirs();
        }
    }

    public User getUser() {
        return user;
    }

}
