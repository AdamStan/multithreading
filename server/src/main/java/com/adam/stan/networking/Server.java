package com.adam.stan.networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.ServerRunner;
import com.adam.stan.security.ApplicationParameters;

public class Server {

    static final Logger logger = Logger.getLogger(Server.class.getName());

    public void handleNewClients() {
        try {
            ServerSocket server = new ServerSocket(ApplicationParameters.PORT);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept(); // server accept the
                                                       // client connection
                                                       // request
                logger.info(" >> " + "Client No:" + counter + " started!");
                ServerForClientThread sct = new ServerForClientThread(
                        serverClient, counter); // send the request to a
                                                // separate thread
                ServerRunner.GLOBAL_WORKER_POOL.execute(sct);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
        }
    }

    /**
     * EXAMPLE of usage with main in Client
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Server().handleNewClients();
    }

}
