package com.adam.stan.connection.files;

import java.util.Collections;
import java.util.List;

public class MockFile implements File {

    private String name;
    private final int timeToDownload;

    public MockFile(String name, int time) {
        this.name = name;
        this.timeToDownload = time;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Resource> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void download() {
        try {
            Thread.sleep(timeToDownload);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
