package com.adam.stan.storage.files;

import java.io.File;

import com.adam.stan.files.Resource;

public class ResourceFactory {

    private final String rootPath;

    public ResourceFactory(String path) {
        this.rootPath = path;
    }

    public Resource getResource(File fileOrDir) {
        if (fileOrDir.isFile()) {
            return new FileImpl(fileOrDir, rootPath);
        }
        return new FolderImpl(fileOrDir, rootPath);
    }

}
