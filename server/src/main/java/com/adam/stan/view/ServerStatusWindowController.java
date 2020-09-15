package com.adam.stan.view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.ServerRunner;
import com.adam.stan.security.User;
import com.adam.stan.storage.ChangeServerRootListener;
import com.adam.stan.storage.RootDirectory;
import com.adam.stan.storage.WatchServerDirectoryThread;
import com.adam.stan.thread.CommonThread;
import com.adam.stan.threads.Informative;
import com.adam.stan.threads.ThreadStatus;
import com.adam.stan.view.icon.IconForFile;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ServerStatusWindowController implements ChangeServerRootListener {

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

    @FXML
    private TreeView<String> treeView;

    private File root;

    public ServerStatusWindowController() {
        root = new File(RootDirectory.NAME);
    }

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

    @FXML
    public void refreshTreeItems() {
        LOGGER.log(Level.INFO, "Tree view: " + treeView);
        if (treeView != null) {
            File[] usersFile = root.listFiles();
            IconForFile iconProvider = new IconForFile();
            TreeItem<String> treeViewRoot = new TreeItem<String>(RootDirectory.NAME, iconProvider.getRootIcon());
            if (usersFile != null) {
                for (File file : usersFile) {
                    addChild(treeViewRoot, file);
                }
            }

            treeView.setRoot(treeViewRoot);
        }
    }

    private void addChild(TreeItem<String> root, File file) {
        IconForFile provider = new IconForFile();
        TreeItem<String> childItem = new TreeItem<>(file.getName(), provider.getIcon(file));
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                addChild(childItem, child);
            }
        }
        root.getChildren().add(childItem);
    }

    @Override
    public void directoryChanged(WatchEvent<?> watchEvent) {
        Platform.runLater(() -> refreshTreeItems());
    }
    
    public void startWatching() {
        Path rootPath = root.toPath();
        WatchServerDirectoryThread directoryWatcher = new WatchServerDirectoryThread(rootPath);
        directoryWatcher.addListener(this);
        ServerRunner.GLOBAL_WORKER_POOL.execute(directoryWatcher);
    }

}
