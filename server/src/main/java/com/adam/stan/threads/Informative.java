package com.adam.stan.threads;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public interface Informative {

    StringProperty getPropertyName();
    ObservableValue<ThreadStatus> getPropertyStatus();
    StringProperty getPropertyDescription();
}
