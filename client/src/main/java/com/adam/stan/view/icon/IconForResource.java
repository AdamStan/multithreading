package com.adam.stan.view.icon;


import com.adam.stan.files.Resource;
import com.adam.stan.storage.files.Folder;

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
