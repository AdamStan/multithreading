package com.adam.stan.storage.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderImpl implements Folder {

    private final File file;

    FolderImpl(File file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public List<Resource> getChildren() {
        List<Resource> children = new ArrayList<>();
        ResourceFactory factory = new ResourceFactory();
        File[] files = file.listFiles();
        for (File file : files) {
            children.add(factory.getResource(file));
        }
        return children;
    }

    @Override
    public void download() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addChildren(Resource child) {
        // TODO Should add to children new resource
    }

}
