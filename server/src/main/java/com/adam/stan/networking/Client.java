package com.adam.stan.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.InfoMessage;
import com.adam.stan.security.User;

public class Client {

    private static Socket socket;
    private static ObjectInputStream inStream;
    private static ObjectOutputStream outStream;
    private static User user = new User("adam");

    public static void main(String[] args) throws Exception {
        init();
        sendInfo();
        sendInfo();
        sendInfo();
        close();
    }

    public static void sendInfo() {
        try {
            ClientServerMessage clientMessage;
            ClientServerMessage serverMessage = null;

            System.out.println("Enter number :");
            clientMessage = new InfoMessage(user, "This is client!");
            outStream.writeObject(clientMessage);
            outStream.flush();
            serverMessage = (ClientServerMessage) inStream.readObject();
            System.out.println(serverMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void init() {
        try {
            socket = new Socket("127.0.0.1", 5001);
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            outStream.close();
            outStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
