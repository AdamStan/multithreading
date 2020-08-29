package com.adam.stan.view;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.security.User;
import com.adam.stan.thread.CommonThread;
import com.adam.stan.threads.Informative;
import com.adam.stan.threads.ThreadStatus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ServerStatusWindowController {

    private static final Logger LOGGER = Logger.getLogger(ServerStatusWindowController.class.getName());

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

    @FXML
    private TableView<Informative> tableOfJavaThreads;

    @FXML
    private TableColumn<Informative, String> javaThreadNames;

    @FXML
    private TableColumn<Informative, String> javaThreadStatus;

    public void setTableInformation(ObservableList<Informative> list) {
        threadName.setCellValueFactory(cellData -> cellData.getValue().getPropertyName());
        threadStatus.setCellValueFactory(cellData -> cellData.getValue().getPropertyStatus());
        threadDescription.setCellValueFactory(cellData -> cellData.getValue().getPropertyDescription());
        tableOfThreads.setItems(list);
    }

    @FXML
    public void showDetails() {
        LOGGER.log(Level.INFO, "Show details");
    }

    @FXML
    public void refreshThreads() {
        LOGGER.info("Refresh java threads");
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        ObservableList<Informative> threads = FXCollections.observableArrayList();
        for (Thread thread : threadSet) {
            threads.add(new CommonThread(thread));
        }
        javaThreadNames.setCellValueFactory(cellData -> cellData.getValue().getPropertyName());
        javaThreadStatus.setCellValueFactory(cellData -> cellData.getValue().getPropertyDescription());
        tableOfJavaThreads.setItems(threads);
    }

    public void setUsersTable(ObservableList<User> users) {
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        tableOfUsers.setItems(users);
    }

}
