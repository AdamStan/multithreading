package com.adam.stan;

import javafx.stage.Stage;

public enum ApplicationPrimaryStage {
    INSTANCE;

    private Stage primaryStage;
    
    void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public synchronized Stage getPrimaryStage() {
        return primaryStage;
    }

}
