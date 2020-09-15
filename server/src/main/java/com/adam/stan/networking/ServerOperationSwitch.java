package com.adam.stan.networking;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.adam.stan.UsersList;
import com.adam.stan.files.FileInfo;
import com.adam.stan.messages.ClientServerMessage;
import com.adam.stan.messages.FilesMessage;
import com.adam.stan.messages.InfoMessage;
import com.adam.stan.storage.ListAllFiles;
import com.adam.stan.storage.UserRootDirectory;

/**
 * Refactor for this class is needed
 * 
 * @author Adam
 *
 */
public class ServerOperationSwitch {

    private static final Logger LOG = Logger.getLogger(ServerOperationSwitch.class.getName());

    public static ClientServerMessage execute(ClientServerMessage clientMessage) {
        switch (clientMessage.getOperationType()) {
        case LOGIN:
            return makeLogin(clientMessage);
        case INFO:
            return makeInfo(clientMessage);
        case DISCONNECT:
            return makeDisconnect(clientMessage);
        case FILE_REFRESH:
            return makeRefreshFiles(clientMessage);
        case FILES_DOWNLOAD:
            return prepareAllFiles(clientMessage);
        case FILE_REMOVE:
            return fileRemoved(clientMessage);
        default:
            return null;
        }
    }

    private static ClientServerMessage makeInfo(ClientServerMessage clientMessage) {
        return new InfoMessage(clientMessage.getUser(), "This is an answer from server");
    }

    private static ClientServerMessage makeLogin(ClientServerMessage clientMessage) {
        // TODO: prepare everything to transfer files on server
        UsersList.INSTANCE.addUser(clientMessage.getUser());
        return new InfoMessage(clientMessage.getUser(), "Everything was fine, you are logged");
    }

    private static ClientServerMessage makeDisconnect(ClientServerMessage clientMessage) {
        UsersList.INSTANCE.removeUser(clientMessage.getUser());
        return null;
    }

    private static ClientServerMessage makeRefreshFiles(ClientServerMessage clientMessage) {
        LOG.info(clientMessage.getValue().get().toString());
        Optional<?> fileResource = clientMessage.getValue();
        UserRootDirectory rootUser = new UserRootDirectory(clientMessage.getUser());
        fileResource.ifPresent(resource -> {
            FileInfo info = (FileInfo) resource;
            LOG.info(info.toString());
            rootUser.createServerFile(info);
        });
        return new InfoMessage(clientMessage.getUser(), "Files were updated");
    }

    private static ClientServerMessage prepareAllFiles(ClientServerMessage clientMessage) {
        UserRootDirectory rootUser = new UserRootDirectory(clientMessage.getUser());
        String rootPath = rootUser.getRootFile().getAbsolutePath();
        ListAllFiles lister = new ListAllFiles(rootPath);

        List<FileInfo> fileInfos = lister.prepareAllFiles();
        return new FilesMessage(clientMessage.getUser(), fileInfos);
    }

    private static ClientServerMessage fileRemoved(ClientServerMessage clientMessage) {
        Optional<?> optionalFile = clientMessage.getValue();
        optionalFile.ifPresent(file -> {
            FileInfo delFile = (FileInfo) file;
            String relPath = delFile.getRelativePath();
            UserRootDirectory root = new UserRootDirectory(clientMessage.getUser());
            File fileToDelete = new File(root.getRootFile().getAbsolutePath() + File.separator + relPath);
            LOG.info("File to delete: " + fileToDelete.toString());
            try {
                while (fileToDelete.exists()) {
                    Files.walk(fileToDelete.toPath()).map(Path::toFile).forEachOrdered(File::delete);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return null;
    }

}
