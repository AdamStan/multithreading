package com.adam.stan.connection.files;

import java.util.List;

public interface Resource {
    String getName();
    List<Resource> getChildren();
    void download();
}
