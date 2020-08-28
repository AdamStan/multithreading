package com.adam.stan.files;

import java.io.Serializable;
import java.util.List;

public interface Resource extends Serializable {

    String getName();
    List<Resource> getChildren();
    void download();
    // TODO: String relativePath();
}
