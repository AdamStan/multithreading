package com.adam.stan.storage.files;

import java.util.Collections;
import java.util.List;


public class FileImpl implements File {
    
    private final java.io.File file;
    private String relativePath;
    
    public FileImpl(java.io.File file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public List<Resource> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void download() {
        // TODO: create something
    }

}
