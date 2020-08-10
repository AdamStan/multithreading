package com.adam.stan.view;

import java.io.IOException;

import com.adam.stan.ApplicationPrimaryStage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrimaryStageSceneChanger {

    public void showUserPanel(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryStageSceneChanger.class.getResource("UserPanel.fxml"));
        try {
            BorderPane rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            UserPanelController controller = loader.getController();
            controller.setLocalStorage(path);
            Stage primaryStage = ApplicationPrimaryStage.INSTANCE.getPrimaryStage(); 
            primaryStage.setScene(scene);
            primaryStage.setTitle("My cloud - user's panel");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
