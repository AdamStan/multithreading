package com.adam.stan.messages;

import java.io.Serializable;
import java.util.Optional;

import com.adam.stan.security.User;

public interface ClientServerMessage extends Serializable {

    Optional<?> getValue();
    User getUser();
}
