package com.adam.stan.threads;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

class Worker extends Thread implements Informative {

    private static final int WAITING_FOR_NEW_TASK_TIME = 5_000;
    private Runnable currentTask;
    private WorkerPool pool;
    private SimpleObjectProperty<ThreadStatus> status;
    private SimpleStringProperty description = new SimpleStringProperty();

    public Worker(Runnable currentTask, WorkerPool pool, String name) {
        super(name);
        this.currentTask = currentTask;
        this.pool = pool;
        status = new SimpleObjectProperty<ThreadStatus>(ThreadStatus.CREATED);
        description.set(currentTask.toString());
    }

    @Override
    public StringProperty getPropertyName() {
        return new SimpleStringProperty(getName());
    }

    @Override
    public ObservableValue<ThreadStatus> getPropertyStatus() {
        return status;
    }

    @Override
    public StringProperty getPropertyDescription() {
        return description;
    }

    @Override
    public void run() {
        // wez aktualnego taska
        // zrob go
        // pobierz nastepnego taska z queue listy working poola
        // jesli juz wszystko z kolejki wykonane to usun sie z working poola
        while (currentTask != null) {
            this.status.set(ThreadStatus.RUNNING);
            currentTask.run();
            currentTask = pool.taskFromQueue();
            if (currentTask == null) {
                waitingForNewTask();
            }
        }

        this.status.set(ThreadStatus.END_OF_LIFE);
        pool.endWorker(this);
    }
    
    private void waitingForNewTask() {
        try {
            status.set(ThreadStatus.SLEEPING);
            Thread.sleep(WAITING_FOR_NEW_TASK_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ThreadStatus getStatus() {
        return status.get();
    }

    public void setNextTask(Runnable runnable) {
        currentTask = runnable;
    }
    
    public void setDescription(String desc) {
        description.set(desc);
    }

}
