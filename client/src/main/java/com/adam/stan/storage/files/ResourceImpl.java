package com.adam.stan.storage.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.List;

import com.adam.stan.files.Resource;

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
    
    /**
     * @return can return -1!
     */
    @Override
    public long getLastModifiedDate() {
        Path path = Paths.get(file.getAbsolutePath());
        BasicFileAttributes attr;
        long time = -1;
        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            time = attr.lastModifiedTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return time;
    }

}
