package com.adam.stan.threads;

import java.util.ArrayList;
import java.util.List;

public class WorkerPool {
    private static final int MAX_WORKERS = 32;
    
    private final List<Worker> workers = new ArrayList<>(MAX_WORKERS);
    
    

}
