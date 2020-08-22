package com.adam.stan.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkerPool {

    private static int MAX_WORKERS = 8;
    private static final int INITIAL_QUEUE_SIZE = 32;
    private int counter;

    private BlockingQueue<Runnable> queueTasks = new ArrayBlockingQueue<>(
            INITIAL_QUEUE_SIZE);
    private final ObservableList<Informative> workers = FXCollections
            .observableArrayList();

    /**
     * There should be parameters: which implementation of BlockingQueue and
     * List should be used but it is not open source library, ok?
     */
    public WorkerPool() {
        // create workers
    }

    public synchronized void execute(Runnable runnable) {
        // get first not busy worker, or add to queue
        for (Informative worker : workers) {
            if (worker.getStatus() == ThreadStatus.SLEEPING) {
                ((Worker) worker).setNextTask(runnable);
                return;
            }
        }
        // jesli jest jakis worker ktory spi to daj mu taska
        // jesli wszystkie zajete i max workerów nie został osiągniety to dodaj
        // nowego workera
        // jesli max został osiągnięty to dodaj do queue listy
        if (workers.size() < MAX_WORKERS) {
            Worker newWorker = new Worker(runnable, this,
                    "Worker-" + counter++);
            workers.add(newWorker);
            newWorker.start();
        } else {
            queueTasks.add(runnable);
        }
    }

    synchronized void endWorker(Informative worker) {
        workers.remove(worker);
    }

    synchronized Runnable taskFromQueue() {
        return queueTasks.poll();
    }

    public ObservableList<Informative> getWorkers() {
        return workers;
    }

    public void interruptAll() {
        queueTasks.clear();
        workers.forEach(work -> {
            this.endWorker(work);
            ((Worker) work).interrupt();
        });
    }

}
