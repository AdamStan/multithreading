package com.adam.stan.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderImpl extends ResourceImpl implements Folder {

    private static final long serialVersionUID = -2182150057701144582L;

    FolderImpl(File file, String rootPath) {
        super(file, rootPath);
    }

    @Override
    public List<Resource> getChildren() {
        List<Resource> children = new ArrayList<>();
        ResourceFactory factory = new ResourceFactory(rootPath);
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
