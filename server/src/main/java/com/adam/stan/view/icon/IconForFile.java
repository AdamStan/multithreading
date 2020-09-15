package com.adam.stan.view.icon;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconForFile {

    private final String FILE_ICON_PATH = "file_icon.png";
    private final String FOLDER_ICON_PATH = "folder_icon.png";
    private final String ROOT_ICON_PATH = "root_icon.png";

    public IconForFile() {
    }

    public Node getIcon(File file) {
        if (file.isFile()) {
            return getFileIcon();
        }
        return getFolderIcon();
    }

    public Node getFileIcon() {
        return new ImageView(new Image(getClass().getResourceAsStream(FILE_ICON_PATH)));
    }

    public Node getFolderIcon() {
        return new ImageView(new Image(getClass().getResourceAsStream(FOLDER_ICON_PATH)));
    }

    public Node getRootIcon() {
        return new ImageView(new Image(getClass().getResourceAsStream(ROOT_ICON_PATH)));
    }
}
