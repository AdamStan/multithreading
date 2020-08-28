package com.adam.stan.files;

import java.util.Collections;
import java.util.List;

public class FileImpl implements File {

    private static final long serialVersionUID = 6712144213192177367L;
    private final java.io.File file;

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

    @Override
    public String toString() {
        return "FileImpl [file=" + file.getAbsolutePath() + "]";
    }

}
