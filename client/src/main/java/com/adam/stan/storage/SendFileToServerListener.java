package com.adam.stan.storage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

import com.adam.stan.connection.Connector;
import com.adam.stan.files.Resource;
import com.adam.stan.storage.files.ResourceFactory;
import com.adam.stan.storage.threads.ChangeInRootListener;

public class SendFileToServerListener implements ChangeInRootListener {

    private RootLocalDirectory root;

    public SendFileToServerListener(RootLocalDirectory root) {
        this.root = root;
    }

    @Override
    public void directoryChanged(WatchEvent<?> key, Path fullPath) {
        System.out.println(key);
        File file = fullPath.toFile();
        ResourceFactory factory = new ResourceFactory(root.getPath());
        Resource resource = factory.getResource(file);

        if (key.kind().name().equals("ENTRY_DELETE")) {
            Connector.deletedFile(resource);
        } else {
            List<Resource> resources = new ArrayList<>();
            List<Resource> descendants = resource.listDescendants();
            System.out.println(descendants);
            resources.add(resource);
            resources.addAll(descendants);
            Connector.sendFiles(resources);
        }
    }

}
