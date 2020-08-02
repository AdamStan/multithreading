package com.adam.stan.view;

import com.adam.stan.connection.ConnectException;
import com.adam.stan.connection.Connector;

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
            connector.getUserRootItems();
        } catch (ConnectException e) {
            e.getCause().printStackTrace();
            // show message in login window
        }
    }
}
