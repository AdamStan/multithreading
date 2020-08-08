package com.adam.stan.threads;

import java.util.List;
import java.util.ArrayList;

public enum MockThreadPool {
    INSTANCE;
    
    private List<InformationProvider> pool = new ArrayList<>();
    
    private MockThreadPool() {
        pool.add(new MockThread("thread1", "description1"));
        pool.add(new MockThread("thread2", "description2"));
        pool.add(new MockThread("thread3", "description3"));
        pool.add(new MockThread("thread4", "description4"));
    }
    
    public List<InformationProvider> getThreads() {
        return pool;
    }

}
