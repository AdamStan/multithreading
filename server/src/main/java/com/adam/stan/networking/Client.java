package com.adam.stan.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.Disconnect;
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
public class Client {

    public static final Logger logger = Logger.getLogger(Client.class.getName());
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private User user;

    public Client(User user) {
        this.user = user;
    }

    public void sendInfo(String message) {
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
    
    public void sendDisconnect() {
        try {
            ClientServerMessage clientMessage = new Disconnect(user);
            outStream.writeObject(clientMessage);
            outStream.flush();
            close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void sendInitialMessage() {
        try {
            ClientServerMessage clientMessage = new LoginMessage(user);
            outStream.writeObject(clientMessage);
            outStream.flush();
            ClientServerMessage serverMessage = (ClientServerMessage) inStream.readObject();
            logger.info(serverMessage.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void sendFile() {
        // TODO: implement
    }

    public void init() {
        try {
            socket = new Socket(ApplicationParameters.HOSTNAME,
                    ApplicationParameters.PORT);
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void close() {
        try {
            outStream.close();
            outStream.close();
            socket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Example of usage with main in Server
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        User userExample = new User("adam");
        Client client = new Client(userExample);
        client.init();
        client.sendInitialMessage();
        client.sendInfo("This is client!");
        client.sendDisconnect();
    }

}
