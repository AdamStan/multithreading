package com.adam.stan.view.icon;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum IconProvider {
    INSTANCE;
    
    private final Node FOLDER_ICON = new ImageView(
            new Image(getClass().getResourceAsStream("folder_icon.png")));

    private final Node FILE_ICON = new ImageView(
            new Image(getClass().getResourceAsStream("file_icon.png")));

    public Node getFileIcon() {
        return FILE_ICON;
    }

    public Node getFolderIcon() {
        return FOLDER_ICON;
    }

}
