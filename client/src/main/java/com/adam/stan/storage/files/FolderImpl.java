package com.adam.stan.storage.files;

import java.util.Collections;
import java.util.List;


public class FolderImpl implements Folder {
    private final java.io.File file;
    
    FolderImpl(java.io.File file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public List<Resource> getChildren() {
        // TODO Should ls
        return Collections.emptyList();
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
