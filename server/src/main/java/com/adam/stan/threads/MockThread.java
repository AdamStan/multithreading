package com.adam.stan.threads;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class MockThread implements InformationProvider {

    private String threadName;
    private String description;
    private ThreadStatus status;

    public MockThread(String threadName, String description) {
        this.threadName = threadName;
        this.description = description;
        this.status = ThreadStatus.CREATED;
    }

    @Override
    public String getName() {
        return threadName;
    }

    @Override
    public ThreadStatus getStatus() {
        return status;
    }

    public void setStatus(ThreadStatus status) {
        this.status = status;
    }

    @Override
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
