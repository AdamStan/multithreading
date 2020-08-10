package com.adam.stan.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private TableView<Informative> table;

    @FXML
    private TableColumn<Informative, String> threadName;

    @FXML
    private TableColumn<Informative, ThreadStatus> threadStatus;

    @FXML
    private TableColumn<Informative, String> threadDescription;

    public void setTableInformation(List<Informative> list) {
        threadName.setCellValueFactory(cellData -> cellData.getValue().getPropertyName());
        threadStatus.setCellValueFactory(cellData -> cellData.getValue().getPropertyStatus());
        threadDescription.setCellValueFactory(cellData -> cellData.getValue().getPropertyDescription());
        ObservableList<Informative> obsList = FXCollections.observableArrayList(list);
        table.setItems(obsList);
    }
    
    @FXML
    public void showDetails() {
        LOGGER.log(Level.INFO, "Show details");
    }

}
