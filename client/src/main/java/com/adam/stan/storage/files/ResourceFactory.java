package com.adam.stan.storage.files;

public class ResourceFactory {

    public Resource getResource(java.io.File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            return new FolderImpl(fileOrDir);
        }
        return new FileImpl(fileOrDir);
    }

}
