package com.adam.stan.files;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public abstract class ResourceImpl implements Resource {

    private static final long serialVersionUID = 1L;
    protected final java.io.File file;
    protected final String rootPath;

    public ResourceImpl(java.io.File file, String rootPath) {
        this.file = file;
        this.rootPath = rootPath;
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
    public String relativePath() {
        return file.getAbsolutePath().substring(rootPath.length());
    }

    @Override
    public byte[] getContent() {
        try {
            if (file.isFile()) 
                return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            // ignore!
        }
        return new byte[0];
    }

}
