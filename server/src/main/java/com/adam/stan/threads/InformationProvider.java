package com.adam.stan.threads;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public interface InformationProvider {

    String getName();
    ThreadStatus getStatus();
    String getDescription();
    StringProperty getPropertyName();
    ObservableValue<ThreadStatus> getPropertyStatus();
    StringProperty getPropertyDescription();
}
