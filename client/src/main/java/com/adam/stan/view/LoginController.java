package com.adam.stan.view;

import com.adam.stan.connection.Connector;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    public void clickExit() {
        System.exit(0);
    }
    
    @FXML
    public void clickLogin() {
        Connector connector = new Connector("localhost");
        
    }
}
