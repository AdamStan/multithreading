package com.adam.stan.storage;

import java.nio.file.WatchEvent;
import java.util.List;

import com.adam.stan.connection.Connector;
import com.adam.stan.files.Resource;
import com.adam.stan.storage.threads.ChangeInRootListener;

public class SendFileToServerListener implements ChangeInRootListener {

    private RootLocalDirectory root;

    public SendFileToServerListener(RootLocalDirectory root) {
        this.root = root;
    }

    @Override
    public void directoryChanged(WatchEvent<?> key) {
        List<Resource> children = root.listRootFiles();
        Connector.sendFiles(children);
    }

}
