package com.adam.stan.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.adam.stan.ClientApplication;
import com.adam.stan.files.FileInfo;
import com.adam.stan.files.Resource;
import com.adam.stan.storage.files.ResourceFactory;
import com.adam.stan.storage.threads.ChangeInRootListener;
import com.adam.stan.storage.threads.WatchDirectoryThread;

public class RootLocalDirectory {

    public static final String ourDirectoryName = "my-cloud";
    public static final String initPath = System.getProperty("user.home") + File.separatorChar + ourDirectoryName;
    private static final Logger LOGGER = Logger.getLogger(RootLocalDirectory.class.getName());

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

    public synchronized List<Resource> listRootFiles() {
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
    
    public synchronized List<Resource> listAllFiles() {
        List<Resource> rootItems = listRootFiles();
        List<Resource> otherChildren = new ArrayList<>();
        rootItems.forEach(file -> {
            otherChildren.addAll(file.getChildren());
        });

        List<Resource> allFiles = new ArrayList<>(rootItems.size() + otherChildren.size());
        allFiles.addAll(rootItems);
        allFiles.addAll(otherChildren);
        return allFiles;
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
    
    /**
     * TODO: duplicate from UserLocalDirectory, pls fix
     * @param file
     */
    public void createLocalFile(FileInfo file) {
        String relativePath = file.getRelativePath();
        String globalPathStart = root.getAbsolutePath();
        File fileOrDirectory = new File(globalPathStart + relativePath);

        if (file.isFile()) {
            try {
                if (!fileOrDirectory.exists()) {
                    LOGGER.info("file: " + file.getRelativePath() + " not exists, will be created.");
                    fileOrDirectory.createNewFile();
                } else {
                    //compare
                    long time = fileOrDirectory.lastModified();
                    if (time == file.getModificationDate()) {
                        LOGGER.info("file: " + file.getRelativePath() + " exists and was not changed");
                        return;
                    }
                }
                Files.write(fileOrDirectory.toPath(), file.getContent(), StandardOpenOption.WRITE);
                // set modification date
                fileOrDirectory.setLastModified(file.getModificationDate());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileOrDirectory.mkdirs();
        }
    }

    @Override
    public String toString() {
        return "RootLocalDirectory [root=" + root + "]";
    }
    
    
}
