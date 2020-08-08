package com.adam.stan.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.threads.InformationProvider;
import com.adam.stan.threads.ThreadStatus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ServerStatusWindowController {
    private static final Logger LOGGER = Logger.getLogger(ServerStatusWindowController.class.getName());

    @FXML
    private TableView<InformationProvider> table;

    @FXML
    private TableColumn<InformationProvider, String> threadName;

    @FXML
    private TableColumn<InformationProvider, ThreadStatus> threadStatus;

    @FXML
    private TableColumn<InformationProvider, String> threadDescription;

    public void setTableInformation(List<InformationProvider> list) {
        threadName.setCellValueFactory(cellData -> cellData.getValue().getPropertyName());
        threadStatus.setCellValueFactory(cellData -> cellData.getValue().getPropertyStatus());
        threadDescription.setCellValueFactory(cellData -> cellData.getValue().getPropertyDescription());
        ObservableList<InformationProvider> obsList = FXCollections.observableArrayList(list);
        table.setItems(obsList);
    }
    
    @FXML
    public void showDetails() {
        LOGGER.log(Level.INFO, "Show details");
    }

}
