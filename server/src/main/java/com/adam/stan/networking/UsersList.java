package com.adam.stan.networking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.adam.stan.security.User;

public enum UsersList {

    INSTANCE;

    private static final List<User> users = new ArrayList<>();

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
}
