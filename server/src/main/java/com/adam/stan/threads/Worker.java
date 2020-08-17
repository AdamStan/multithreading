package com.adam.stan.threads;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

class Worker extends Thread implements Informative {

    private static final int WAITING_FOR_NEW_TASK = 1_000;
    private Runnable currentTask;
    private WorkerPool pool;
    private ThreadStatus status = ThreadStatus.CREATED;

    public Worker(Runnable currentTask, WorkerPool pool, String name) {
        super(name);
        this.currentTask = currentTask;
        this.pool = pool;
    }

    @Override
    public StringProperty getPropertyName() {
        return new SimpleStringProperty(getName());
    }

    @Override
    public ObservableValue<ThreadStatus> getPropertyStatus() {
        return new SimpleObjectProperty<ThreadStatus>(status);
    }

    @Override
    public StringProperty getPropertyDescription() {
        return new SimpleStringProperty("description should be here");
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // wez aktualnego taska
        // zrob go
        // pobierz nastepnego taska z queue listy working poola
        // jesli juz wszystko z kolejki wykonane to usun sie z working poola
        while (currentTask != null) {
            this.status = ThreadStatus.RUNNING;
            currentTask.run();
            currentTask = pool.taskFromQueue();
            if (currentTask == null) {
                waitingForNewTask();
            }
        }

        this.status = ThreadStatus.END_OF_LIFE;
        pool.endWorker(this);
    }
    
    private void waitingForNewTask() {
        try {
            status = ThreadStatus.SLEEPING;
            Thread.sleep(WAITING_FOR_NEW_TASK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ThreadStatus getStatus() {
        return status;
    }

    public void setNextTask(Runnable runnable) {
        currentTask = runnable;
    }

}
