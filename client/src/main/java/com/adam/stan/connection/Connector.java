package com.adam.stan.connection;

import java.util.ArrayList;
import java.util.List;

import com.adam.stan.connection.files.Folder;
import com.adam.stan.connection.files.MockFile;
import com.adam.stan.connection.files.MockFolder;
import com.adam.stan.connection.files.Resource;

public class Connector {

    private final String url;
    private static final List<Resource> resources = new ArrayList<>();
    
    static {
        resources.add(new MockFile("file1.txt", 10_000));
        resources.add(new MockFile("file2.xml", 10_000));
        resources.add(new MockFile("file3.py", 10_000));
        Folder folder = new MockFolder("folder");
        folder.addChildren(new MockFile("file_in_folder1.txt", 2_000));
        folder.addChildren(new MockFile("file_in_folder2.txt", 2_000));
        resources.add(folder);
    }

    public Connector(String address) {
        url = address;
    }

    public void connect() throws ConnectException {
        try {
            // TODO: make connection
        } catch (Exception e) {
            throw new ConnectException(e);
        }
    }

    public List<Resource> getUserRootItems() {
        return resources;
    }
}
