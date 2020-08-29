package com.adam.stan.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.Disconnect;

public class ServerForClientThread extends Thread {

    private Socket serverClient;
    private int clientID;

    public ServerForClientThread(Socket serverClient, int clientID) {
        this.serverClient = serverClient;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        try {
            Server.logger.log(Level.INFO, "Started thread for client");
            ObjectOutputStream outStream = new ObjectOutputStream(
                    serverClient.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(
                    serverClient.getInputStream());
            Server.logger.log(Level.INFO, "Streams were created");
            ClientServerMessage clientMessage;
            ClientServerMessage serverMessage;
            do {
                Object object = inStream.readObject();
                System.out.println(object);
                clientMessage = (ClientServerMessage) object;
                Server.logger.log(Level.INFO, "From client: " + clientID + ", message is: " + clientMessage);
                serverMessage = findCorrectAnswer(clientMessage);
                outStream.writeObject(serverMessage);
                outStream.flush();
            } while (clientMessageNotDisconnect(clientMessage));

            inStream.close();
            outStream.close();
            serverClient.close();
        } catch (IOException e) {
            Server.logger.log(Level.INFO, e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            Server.logger.log(Level.INFO, e.getMessage(), e);
        }
    }

    private boolean clientMessageNotDisconnect(
            ClientServerMessage clientMessage) {
        return clientMessage != null && clientMessage.getValue().isPresent()
                && !clientMessage.getValue().get().equals(Disconnect.message);
    }
    
    private ClientServerMessage findCorrectAnswer(ClientServerMessage clientMessage) {
        return ServerOperationSwitch.execute(clientMessage);
    }

}
