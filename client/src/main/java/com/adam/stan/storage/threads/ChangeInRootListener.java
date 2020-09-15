package com.adam.stan.storage.threads;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

public interface ChangeInRootListener {

    void directoryChanged(WatchEvent<?> key, Path fullPath);
}
