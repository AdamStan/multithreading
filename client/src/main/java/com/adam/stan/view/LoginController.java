package com.adam.stan.view;

import java.util.List;

import com.adam.stan.connection.ConnectException;
import com.adam.stan.connection.Connector;
import com.adam.stan.connection.files.Resource;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label errorLabel;

    @FXML
    public void clickExit() {
        System.exit(0);
    }
    
    @FXML
    public void clickLogin() {
        Connector connector = new Connector("localhost");
        try {
            connector.connect();
            List<Resource> items = connector.getUserRootItems();
            PrimaryStageSceneChanger changer = new PrimaryStageSceneChanger();
            changer.showUserPanel(items);
        } catch (ConnectException e) {
            e.getCause().printStackTrace();
            // show message in login window
        }
    }
}
