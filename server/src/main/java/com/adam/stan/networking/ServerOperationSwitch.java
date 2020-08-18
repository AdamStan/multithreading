package com.adam.stan.networking;

import com.adam.stan.UsersList;
import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.InfoMessage;

public class ServerOperationSwitch {

    public static ClientServerMessage execute(
            ClientServerMessage clientMessage) {
        switch (clientMessage.getOperationType()) {
        case LOGIN:
            return makeLogin(clientMessage);
        case INFO:
            return makeInfo(clientMessage);
        case DISCONNECT:
            return makeDisconnect(clientMessage);
        default:
            return null;
        }
    }

    private static ClientServerMessage makeInfo(
            ClientServerMessage clientMessage) {
        return new InfoMessage(clientMessage.getUser(),
                "This is an answer from server");
    }

    private static ClientServerMessage makeLogin(
            ClientServerMessage clientMessage) {
        // TODO: prepare everything to transfer files on server
        UsersList.INSTANCE.addUser(clientMessage.getUser());
        return new InfoMessage(clientMessage.getUser(),
                "Everything was fine, you are logged");
    }

    private static ClientServerMessage makeDisconnect(
            ClientServerMessage clientMessage) {
        UsersList.INSTANCE.removeUser(clientMessage.getUser());
        return null;
    }

}
