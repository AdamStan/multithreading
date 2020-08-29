package com.adam.stan.files;

public class FileImpl extends ResourceImpl implements File {

    private static final long serialVersionUID = 6712144213192177367L;

    public FileImpl(java.io.File file, String rootPath) {
        super(file, rootPath);
    }

    @Override
    public String toString() {
        return "FileImpl [file=" + file.getAbsolutePath() + "]";
    }

}
