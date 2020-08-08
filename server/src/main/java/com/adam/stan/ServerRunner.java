package com.adam.stan;

import java.io.IOException;

import com.adam.stan.threads.MockThreadPool;
import com.adam.stan.view.ServerStatusWindowController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                ServerRunner.class.getResource("view/ServerStatusWindow.fxml"));
        try {
            BorderPane rootLayout = (BorderPane) loader.load();
            ServerStatusWindowController controller = loader.getController();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My cloud - server");
            primaryStage.show();
            controller.setTableInformation(MockThreadPool.INSTANCE.getThreads());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
