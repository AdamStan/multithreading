package com.adam.stan;

import java.io.IOException;

import com.adam.stan.networking.Server;
import com.adam.stan.threads.WorkerPool;
import com.adam.stan.view.ServerStatusWindowController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerRunner extends Application {

    public static final WorkerPool GLOBAL_WORKER_POOL = new WorkerPool();
    public static final Server GLOBAL_SERVER = new Server();

    public static void main(String[] args) {
        GLOBAL_WORKER_POOL.execute(() -> GLOBAL_SERVER.turnOn());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServerRunner.class.getResource("view/ServerStatusWindow.fxml"));
        try {
            BorderPane rootLayout = (BorderPane) loader.load();
            ServerStatusWindowController controller = loader.getController();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My cloud - server");
            primaryStage.show();
            controller.setTableInformation(GLOBAL_WORKER_POOL.getWorkers());
            controller.setUsersTable(UsersList.INSTANCE.getUsers());
            controller.startWatching();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        GLOBAL_SERVER.turnOff();
        GLOBAL_WORKER_POOL.interruptAll();
        // interupt all threads doesn't work when we are starting wathching
        System.exit(0);
    }

}
