package com.adam.stan.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adam.stan.storage.files.Resource;
import com.adam.stan.storage.files.ResourceFactory;

public class LocalStorage {

    public static final String ourDirectoryName = "my-cloud";
    public static final String initPath = System.getProperty("user.home")
            + File.separatorChar + ourDirectoryName;

    private final String path;
    private File root;
    private List<Resource> children = Collections.emptyList();

    public LocalStorage(String path) {
        this.path = path;
        this.root = new File(path);
    }

    public String getPath() {
        return path;
    }

    public List<Resource> listFiles() {
        children = new ArrayList<>();
        if (!root.exists()) {
            root.mkdir();
        }

        ResourceFactory factory = new ResourceFactory();
        File[] files = root.listFiles();

        for (File file : files) {
            children.add(factory.getResource(file));
        }

        return children;
    }
}
