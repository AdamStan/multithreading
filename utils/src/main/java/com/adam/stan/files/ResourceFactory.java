package com.adam.stan.files;

public class ResourceFactory {

    public Resource getResource(java.io.File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            return new FolderImpl(fileOrDir);
        }
        return new FileImpl(fileOrDir);
    }

}
