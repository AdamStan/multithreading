package com.adam.stan.networking.tasks;

import com.adam.stan.messages.ClientServerMessage;

public interface ServerTask {
    ClientServerMessage doTask();
}
