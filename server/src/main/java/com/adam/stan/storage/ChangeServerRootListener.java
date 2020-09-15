package com.adam.stan.storage;

import java.nio.file.WatchEvent;

public interface ChangeServerRootListener {
    void directoryChanged(WatchEvent<?> watchEvent);
}
