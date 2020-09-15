package com.adam.stan.storage.threads;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class WatchDirectoryThread extends Thread {

    private static final Logger logger = Logger
            .getLogger(WatchDirectoryThread.class.getName());
    private static final String THREAD_NAME = "Watch local directory - thread";
    private final Path pathToObserve;
    private List<ChangeInRootListener> listeners = Collections
            .synchronizedList(new ArrayList<>());

    public WatchDirectoryThread(Path pathToObserve) {
        super(THREAD_NAME);
        this.pathToObserve = pathToObserve;
    }

    public void addListener(ChangeInRootListener listener) {
        listeners.add(listener);
    }

    @Override
    public void run() {
        try {
            final WatchService watcher = FileSystems.getDefault()
                    .newWatchService();
            pathToObserve.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                WatchKey queuedKey;
                queuedKey = watcher.take();
                for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                    String message = "kind= " + watchEvent.kind() + ", count="
                            + watchEvent.count() + ", context="
                            + watchEvent.context() + " Context type="
                            + ((Path) watchEvent.context()).getClass()
                                    .toString();
                    Path dir = (Path)queuedKey.watchable();
                    Path fullPath = dir.resolve((Path) watchEvent.context());
                    logger.info(message);
                    // inform listeners about changes
                    listeners.forEach(
                            listener -> listener.directoryChanged(watchEvent, fullPath));

                    if (!queuedKey.reset()) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
