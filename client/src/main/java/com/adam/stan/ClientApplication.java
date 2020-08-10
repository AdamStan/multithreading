package com.adam.stan;

import java.io.IOException;

import com.adam.stan.storage.LocalStorage;
import com.adam.stan.view.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                ClientApplication.class.getResource("view/Login.fxml"));
        try {
            GridPane rootLayout = (GridPane) loader.load();
            Scene scene = new Scene(rootLayout);
            LoginController controller = loader.getController();
            controller.disableChange();
            controller.setInitialPath(LocalStorage.initPath);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My cloud");
            primaryStage.show();
            ApplicationPrimaryStage.INSTANCE.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
