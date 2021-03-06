package com.adam.stan;

import java.util.Iterator;

import com.adam.stan.security.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum UsersList {

    INSTANCE;

    private static final ObservableList<User> users = FXCollections.observableArrayList();

    public void addUser(User user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public void removeUser(String username) {
        synchronized (users) {
            Iterator<User> usersIterator = users.iterator();
            while (usersIterator.hasNext()) {
                User user = usersIterator.next();
                if (user.getName().equals(username)) {
                    usersIterator.remove();
                }
            }
        }
    }

    public void removeUser(User user) {
        synchronized (users) {
            users.remove(user);
        }
    }

    ObservableList<User> getUsers() {
        return users;
    }
}
