package com.adam.stan.view;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adam.stan.connection.Connector;
import com.adam.stan.files.Resource;
import com.adam.stan.storage.RootLocalDirectory;
import com.adam.stan.storage.SendFileToServerListener;
import com.adam.stan.storage.threads.ChangeInRootListener;
import com.adam.stan.view.icon.IconForResource;
import com.adam.stan.view.icon.IconProvider;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class UserPanelController implements ChangeInRootListener {

    private static final Logger LOGGER = Logger.getLogger(UserPanelController.class.getName());
    private RootLocalDirectory root;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label taskName;

    public void setLocalStorage(String path) {
        root = new RootLocalDirectory(path);
        refreshItems();
        root.watch();
        root.addFileChangedListener(this);
        root.addFileChangedListener(new SendFileToServerListener(root));
    }

    /**
     * Run on JavaFX thread
     */
    private void refreshItems() {
        List<Resource> children = root.listRootFiles();
        loadRootItems(children);
    }

    public void loadRootItems(List<Resource> resources) {
        LOGGER.log(Level.INFO, "Tree view: " + treeView);
        if (treeView != null) {
            TreeItem<String> root = new TreeItem<String>("ROOT", IconProvider.INSTANCE.getFolderIcon());
            resources.forEach(resource -> {
                addChild(root, resource);
            });
            treeView.setRoot(root);
        }
    }

    private void addChild(TreeItem<String> root, Resource child) {
        TreeItem<String> childItem = new TreeItem<>(child.getName(), IconForResource.getIcon(child));
        child.getChildren().forEach(grandchild -> addChild(childItem, grandchild));
        root.getChildren().add(childItem);
    }

    @FXML
    public void disconnect() {
        LOGGER.log(Level.INFO, "Disconnect");
    }

    @FXML
    public void browse() {
        LOGGER.log(Level.INFO, "Browse...");
    }

    /**
     * force upload for all files
     */
    @FXML
    public void upload() {
        LOGGER.log(Level.INFO, "Upload files.");
        List<Resource> allFiles = root.listAllFiles();
        Connector.sendFiles(allFiles);
    }
    
    @FXML
    public void downloadFiles() {
        LOGGER.info("Download file from server.");
        Connector.downloadAllFiles(root);
    }

    @FXML
    public void settings() {
        LOGGER.log(Level.INFO, "settings");
    }

    @Override
    public void directoryChanged(WatchEvent<?> key, Path fullPath) {
        Platform.runLater(() -> this.refreshItems());
    }
}
