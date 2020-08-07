package com.adam.stan.connection.files;

import java.util.ArrayList;
import java.util.List;

public class MockFolder implements Folder {
    private List<Resource> children = new ArrayList<>();
    private String name;

    public MockFolder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Resource> getChildren() {
        return children;
    }

    @Override
    public void addChildren(Resource child) {
        children.add(child);
    }

    @Override
    public void download() {
        children.forEach(child -> child.download());
    }

}
