package com.adam.stan.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderImpl implements Folder {

    private static final long serialVersionUID = -2182150057701144582L;
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

    @Override
    public String toString() {
        return "FolderImpl [file=" + file.getAbsolutePath() + "]";
    }

}
