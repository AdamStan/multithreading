package com.adam.stan.threads;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class MockThread implements Informative {

    private String threadName;
    private String description;
    private ThreadStatus status;

    public MockThread(String threadName, String description) {
        this.threadName = threadName;
        this.description = description;
        this.status = ThreadStatus.CREATED;
    }

    public String getName() {
        return threadName;
    }

    public ThreadStatus getStatus() {
        return status;
    }

    public void setStatus(ThreadStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public StringProperty getPropertyName() {
        return new SimpleStringProperty(threadName);
    }

    @Override
    public ObservableValue<ThreadStatus> getPropertyStatus() {
        return new SimpleObjectProperty<ThreadStatus>(status);
    }

    @Override
    public StringProperty getPropertyDescription() {
        return new SimpleStringProperty(description);
    }

}
