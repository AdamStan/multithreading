package com.adam.stan.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.adam.stan.connection.files.Resource;
import com.adam.stan.view.icon.IconForResource;
import com.adam.stan.view.icon.IconProvider;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class UserPanelController {

    private static final Logger LOGGER = Logger
            .getLogger(UserPanelController.class.getName());

    @FXML
    private TreeView<String> treeView;

    public void loadRootItems(List<Resource> resources) {
        LOGGER.log(Level.INFO, "Tree view: " + treeView);
        if (treeView != null) {
            TreeItem<String> root = new TreeItem<String>("ROOT",
                    IconProvider.INSTANCE.getFolderIcon());
            resources.forEach(resource -> {
                addChild(root, resource);
            });
            treeView.setRoot(root);
        }
    }

    private void addChild(TreeItem<String> root, Resource child) {
        TreeItem<String> childItem = new TreeItem<>(child.getName(),
                IconForResource.getIcon(child));
        child.getChildren()
                .forEach(grandchild -> addChild(childItem, grandchild));
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

    @FXML
    public void refresh() {
        LOGGER.log(Level.INFO, "Refresh");
    }

    @FXML
    public void settings() {
        LOGGER.log(Level.INFO, "settings");
    }
}
