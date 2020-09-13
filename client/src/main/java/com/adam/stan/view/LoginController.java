package com.adam.stan.view;

import java.io.File;

import com.adam.stan.ApplicationPrimaryStage;
import com.adam.stan.connection.Connector;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField username;

    @FXML
    private TextField pathToRootFile;

    @FXML
    private Button changeLocation;

    @FXML
    public void clickExit() {
        System.exit(0);
    }

    public void disableChange() {
        // changeLocation.setDisable(true);
        pathToRootFile.setEditable(false);
    }

    public void setInitialPath(String value) {
        pathToRootFile.setText(value);
    }

    @FXML
    public void clickLogin() {
        Platform.runLater(() -> {
            Connector.connect(username.getText());
            PrimaryStageSceneChanger changer = new PrimaryStageSceneChanger();
            changer.showUserPanel(pathToRootFile.getText());
        });
    }

    @FXML
    public void showDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        Window stage = ApplicationPrimaryStage.INSTANCE.getPrimaryStage();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            pathToRootFile.setText(file.getAbsolutePath());
        }
    }
}
