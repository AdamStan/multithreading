package com.adam.stan.storage.files;

import java.util.List;

public interface Resource {

    String getName();
    List<Resource> getChildren();
    void download();
}
