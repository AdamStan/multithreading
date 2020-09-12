package com.adam.stan.files;

import java.io.Serializable;
import java.util.List;

public interface Resource extends Serializable {

    String getName();
    List<Resource> getChildren();
    String relativePath();
    byte[] getContent();
    boolean isFile();
    long getLastModifiedDate();
}
