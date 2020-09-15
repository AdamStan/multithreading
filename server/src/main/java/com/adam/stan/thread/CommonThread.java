package com.adam.stan.thread;

import com.adam.stan.threads.Informative;
import com.adam.stan.threads.ThreadStatus;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 * Decorator for Thread, just to set it to table view
 * 
 * @author Adam
 *
 */
public class CommonThread extends Thread implements Informative {

    private final Thread trueThread;

    public CommonThread(Thread thread) {
        trueThread = thread;
    }

    @Override
    public StringProperty getPropertyName() {
        return new SimpleStringProperty(trueThread.getName());
    }

    @Override
    public ObservableValue<ThreadStatus> getPropertyStatus() {
        return new SimpleObjectProperty<ThreadStatus>(ThreadStatus.RUNNING);
    }

    @Override
    public StringProperty getPropertyDescription() {
        return new SimpleStringProperty(trueThread.getState().name());
    }

    @Override
    public ThreadStatus getStatus() {
        return ThreadStatus.RUNNING;
    }

}
