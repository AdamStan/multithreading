package com.adam.stan.view.icon;

import com.adam.stan.connection.files.Folder;
import com.adam.stan.connection.files.Resource;

import javafx.scene.Node;

public class IconForResource {

    private IconForResource() {
    }

    public static Node getIcon(Resource resource) {
        if (resource instanceof Folder) {
            return IconProvider.INSTANCE.getFolderIcon();
        }
        return IconProvider.INSTANCE.getFileIcon();
    }
}
