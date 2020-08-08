package com.adam.stan.view;

import java.io.IOException;
import java.util.List;

import com.adam.stan.ApplicationPrimaryStage;
import com.adam.stan.connection.files.Resource;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrimaryStageSceneChanger {

    public void showUserPanel(List<Resource> resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryStageSceneChanger.class.getResource("UserPanel.fxml"));
        try {
            BorderPane rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            UserPanelController controller = loader.getController();
            controller.loadRootItems(resources);
            Stage primaryStage = ApplicationPrimaryStage.INSTANCE.getPrimaryStage(); 
            primaryStage.setScene(scene);
            primaryStage.setTitle("SHOP");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
