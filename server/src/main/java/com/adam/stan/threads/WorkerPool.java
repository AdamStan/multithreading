package com.adam.stan.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkerPool {

    private static final int MAX_WORKERS = 4;
    private static final int INITIAL_QUEUE_SIZE = 32;
    private int counter;

    private BlockingQueue<Runnable> queueTasks = new ArrayBlockingQueue<>(
            INITIAL_QUEUE_SIZE);
    private final List<Worker> workers = new ArrayList<>(MAX_WORKERS);

    /**
     * There should be parameters: which implementation of BlockingQueue and
     * List should be used but it is not open source library, ok?
     */
    public WorkerPool() {
        // create workers
    }

    public synchronized void execute(Runnable runnable) {
        // get first not busy worker, or add to queue
        for (Worker worker : workers) {
            if (worker.getStatus() == ThreadStatus.SLEEPING) {
                worker.setNextTask(runnable);
                return;
            }
        }
        // jesli jest jakis worker ktory spi to daj mu taska
        // jesli wszystkie zajete i max workerów nie został osiągniety to dodaj
        // nowego workera
        // jesli max został osiągnięty to dodaj do queue listy
        if(workers.size() < MAX_WORKERS) {
            Worker newWorker = new Worker(runnable, this, "Worker-" + counter++);
            workers.add(newWorker);
            newWorker.start();
        } else {
            queueTasks.add(runnable);
        }
    }

    synchronized void endWorker(Worker worker) {
        workers.remove(worker);
    }

    synchronized Runnable taskFromQueue() {
        return queueTasks.poll();
    }
    
    public List<Worker> getWorkers() {
        return workers;
    }

}
