package com.adam.stan.networking;

import java.util.List;
import java.util.Optional;

import com.adam.stan.UsersList;
import com.adam.stan.files.Resource;
import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.InfoMessage;

public class ServerOperationSwitch {

    public static ClientServerMessage execute(ClientServerMessage clientMessage) {
        switch (clientMessage.getOperationType()) {
        case LOGIN:
            return makeLogin(clientMessage);
        case INFO:
            return makeInfo(clientMessage);
        case DISCONNECT:
            return makeDisconnect(clientMessage);
        case FILE_REFRESH:
            return makeRefreshFiles(clientMessage);
        default:
            return null;
        }
    }


    private static ClientServerMessage makeInfo(ClientServerMessage clientMessage) {
        return new InfoMessage(clientMessage.getUser(), "This is an answer from server");
    }

    private static ClientServerMessage makeLogin(ClientServerMessage clientMessage) {
        // TODO: prepare everything to transfer files on server
        UsersList.INSTANCE.addUser(clientMessage.getUser());
        return new InfoMessage(clientMessage.getUser(), "Everything was fine, you are logged");
    }

    private static ClientServerMessage makeDisconnect(ClientServerMessage clientMessage) {
        UsersList.INSTANCE.removeUser(clientMessage.getUser());
        return null;
    }

    @SuppressWarnings("unchecked")
    private static ClientServerMessage makeRefreshFiles(ClientServerMessage clientMessage) {
        System.out.println(clientMessage.getValue().get());
        Optional<?> listOfResources = clientMessage.getValue();
        listOfResources.ifPresent(listOfAllResources -> {
            List<Resource> list = (List<Resource>) listOfAllResources;
            list.forEach(res -> {
                System.out.println(res.toString());
            });
        });
        return new InfoMessage(clientMessage.getUser(), "Files were updated");
    }
}
