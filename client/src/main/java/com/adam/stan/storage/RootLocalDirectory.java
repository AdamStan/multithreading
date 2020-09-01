package com.adam.stan.storage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adam.stan.ClientApplication;
import com.adam.stan.files.Resource;
import com.adam.stan.storage.files.ResourceFactory;
import com.adam.stan.storage.threads.ChangeInRootListener;
import com.adam.stan.storage.threads.WatchDirectoryThread;

public class RootLocalDirectory {

    public static final String ourDirectoryName = "my-cloud";
    public static final String initPath = System.getProperty("user.home") + File.separatorChar + ourDirectoryName;

    private final String path;
    private File root;
    private List<Resource> children = Collections.emptyList();
    private WatchDirectoryThread directoryWatcher;

    public RootLocalDirectory(String path) {
        this.path = path;
        this.root = new File(path);
    }

    public String getPath() {
        return path;
    }

    public synchronized List<Resource> listFiles() {
        children = new ArrayList<>();
        if (!root.exists()) {
            root.mkdir();
        }

        ResourceFactory factory = new ResourceFactory(getPath());
        File[] files = root.listFiles();

        for (File file : files) {
            children.add(factory.getResource(file));
        }

        return children;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void watch() {
        Path rootPath = root.toPath();
        directoryWatcher = new WatchDirectoryThread(rootPath);
        ClientApplication.GLOBAL_WORKER_POOL.execute(directoryWatcher);
    }

    public void addFileChangedListener(ChangeInRootListener listener) {
        if (directoryWatcher != null) {
            directoryWatcher.addListener(listener);
        }
    }
}
