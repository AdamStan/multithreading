package com.adam.stan.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.adam.stan.files.FileInfo;

public class UserRootDirectory {

    private final File userDirectory;

    public UserRootDirectory(String username) {
        userDirectory = new File(RootDirectory.NAME + File.separatorChar + username);
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
                    fileOrDirectory.createNewFile();
                }
                Files.write(fileOrDirectory.toPath(), file.getContent(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileOrDirectory.mkdirs();
        }
    }

}
