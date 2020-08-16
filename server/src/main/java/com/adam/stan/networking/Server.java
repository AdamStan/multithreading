package com.adam.stan.networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    static final Logger logger = Logger.getLogger(Server.class.getName());

    public void handleNewClients() {
        try {
            ServerSocket server = new ServerSocket(5001);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept(); // server accept the
                                                       // client connection
                                                       // request
                System.out
                        .println(" >> " + "Client No:" + counter + " started!");
                ServerForClientThread sct = new ServerForClientThread(
                        serverClient, counter); // send the request to a
                                                // separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        new Server().handleNewClients();
    }

}
