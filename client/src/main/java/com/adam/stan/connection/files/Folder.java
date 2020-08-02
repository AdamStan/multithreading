package com.adam.stan.connection.files;

import java.util.List;

public interface Folder extends Resource {
    List<Resource> getChildren(); 
}
