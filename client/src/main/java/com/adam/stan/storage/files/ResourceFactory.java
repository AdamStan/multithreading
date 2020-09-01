package com.adam.stan.storage.files;

import com.adam.stan.files.Resource;

public class ResourceFactory {

    private final String rootPath;

    public ResourceFactory(String path) {
        this.rootPath = path;
    }

    public Resource getResource(java.io.File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            return new FolderImpl(fileOrDir, rootPath);
        }
        return new FileImpl(fileOrDir, rootPath);
    }

}
