package com.adam.stan.storage.files;

import com.adam.stan.files.Resource;

public interface Folder extends Resource {
    void addChildren(Resource child);
}
