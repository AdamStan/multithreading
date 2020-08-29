package com.adam.stan.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.files.Resource;
import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.Disconnect;
import com.adam.stan.messages.FileMessage;
import com.adam.stan.messages.InfoMessage;
import com.adam.stan.messages.LoginMessage;
import com.adam.stan.security.ApplicationParameters;
import com.adam.stan.security.User;

/**
 * Create and then use init to initialize fields, on exit use close
 * 
 * @author Adam
 *
 */
class Client {

    public static final Logger logger = Logger
            .getLogger(Client.class.getName());
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private User user;

    Client(User user) {
        this.user = user;
    }

    void sendInfo(String message) {
        try {
            ClientServerMessage clientMessage = new InfoMessage(user, message);
            ClientServerMessage serverMessage = null;

            outStream.writeObject(clientMessage);
            outStream.flush();
            serverMessage = (ClientServerMessage) inStream.readObject();
            logger.info(serverMessage.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void sendDisconnect() {
        try {
            ClientServerMessage clientMessage = new Disconnect(user);
            outStream.writeObject(clientMessage);
            outStream.flush();
            close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void sendInitialMessage() {
        try {
            ClientServerMessage clientMessage = new LoginMessage(user);
            outStream.writeObject(clientMessage);
            outStream.flush();
            ClientServerMessage serverMessage = (ClientServerMessage) inStream
                    .readObject();
            logger.info(serverMessage.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void sendFiles(List<Resource> resources) {
        try {
            ClientServerMessage clientMessage = new FileMessage(user, resources);
            System.out.println(clientMessage);
            outStream.writeObject(clientMessage);
            outStream.flush();
//            ClientServerMessage serverMessage = (ClientServerMessage) inStream
//                    .readObject();
//            logger.info(serverMessage.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void init() {
        try {
            socket = new Socket(ApplicationParameters.HOSTNAME,
                    ApplicationParameters.PORT);
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void close() {
        try {
            outStream.close();
            outStream.close();
            socket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    boolean isConnected() {
        return inStream != null && outStream != null;
    }

    /**
     * Example of usage with main in Server
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        User userExample = new User("adam");
        Client client = new Client(userExample);
        client.init();
        client.sendInitialMessage();
        client.sendInfo("This is client!");
        Thread.sleep(5_000);
        client.sendDisconnect();
    }

}
