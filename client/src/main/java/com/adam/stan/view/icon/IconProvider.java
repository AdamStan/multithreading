package com.adam.stan.view.icon;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum IconProvider {
    INSTANCE;

    // I've tried to not create icon every time, but JavaFX TreeViewer doesn't
    // support this optimization
    private final Node FOLDER_ICON = new ImageView(
            new Image(getClass().getResourceAsStream("folder_icon.png")));

    private final Node FILE_ICON = new ImageView(
            new Image(getClass().getResourceAsStream("file_icon.png")));
    
    private final String FILE_ICON_PATH = "file_icon.png";
    private final String FOLDER_ICON_PATH = "folder_icon.png";

    public Node getFileIcon() {
        return new ImageView(
                new Image(getClass().getResourceAsStream(FILE_ICON_PATH)));
    }

    public Node getFolderIcon() {
        return new ImageView(
                new Image(getClass().getResourceAsStream(FOLDER_ICON_PATH)));
    }

}
