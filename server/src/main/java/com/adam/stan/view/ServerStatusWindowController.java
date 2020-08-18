package com.adam.stan.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.security.User;
import com.adam.stan.threads.Informative;
import com.adam.stan.threads.ThreadStatus;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ServerStatusWindowController {

    private static final Logger LOGGER = Logger
            .getLogger(ServerStatusWindowController.class.getName());

    @FXML
    private TableView<Informative> tableOfThreads;

    @FXML
    private TableColumn<Informative, String> threadName;

    @FXML
    private TableColumn<Informative, ThreadStatus> threadStatus;

    @FXML
    private TableColumn<Informative, String> threadDescription;

    @FXML
    private TableView<User> tableOfUsers;

    @FXML
    private TableColumn<User, String> usernameColumn;

    public void setTableInformation(ObservableList<Informative> list) {
        threadName.setCellValueFactory(
                cellData -> cellData.getValue().getPropertyName());
        threadStatus.setCellValueFactory(
                cellData -> cellData.getValue().getPropertyStatus());
        threadDescription.setCellValueFactory(
                cellData -> cellData.getValue().getPropertyDescription());
        tableOfThreads.setItems(list);
    }

    @FXML
    public void showDetails() {
        LOGGER.log(Level.INFO, "Show details");
    }

    public void setUsersTable(ObservableList<User> users) {
        usernameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        tableOfUsers.setItems(users);
    }

}
