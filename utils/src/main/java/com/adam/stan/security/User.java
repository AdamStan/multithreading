package com.adam.stan.security;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User implements Serializable {

    private static final long serialVersionUID = 4207980104960853353L;
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User [" + name + "]";
    }

    public StringProperty getNameProperty() {
        return new SimpleStringProperty(name);
    }

}