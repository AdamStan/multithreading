package com.adam.stan.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.adam.stan.files.FileInfo;

public class ListAllFiles {

    private final String rootPath;

    public ListAllFiles(String absolutRootPath) {
        rootPath = absolutRootPath;
    }

    public List<FileInfo> prepareAllFiles() {
        File userRoot = new File(rootPath);
        List<FileInfo> fileInfos = new ArrayList<>();
        List<File> allFiles = new ArrayList<>();
        File[] arrayRootFiles = userRoot.listFiles();
        if (arrayRootFiles == null) {
            return fileInfos;
        }

        List<File> rootFiles = new ArrayList<File>(arrayRootFiles.length);
        for (File file : arrayRootFiles) {
            rootFiles.add(file);
        }

        List<File> restFiles = new ArrayList<>();
        for (File rootFile : rootFiles) {
            restFiles.addAll(listFiles(rootFile));
        }
        allFiles.addAll(rootFiles);
        allFiles.addAll(restFiles);

        for (File file : allFiles) {
            try {
                boolean isFile = file.isFile();

                byte[] content = new byte[0];
                if (isFile) {
                    content = Files.readAllBytes(file.toPath());
                }
                long modificationDate = file.lastModified();
                String relativePath = file.getAbsolutePath().substring(rootPath.length());

                fileInfos.add(new FileInfo(relativePath, content, isFile, modificationDate));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileInfos;
    }

    private Collection<? extends File> listFiles(File rootFile) {
        List<File> files = new ArrayList<>();

        File[] children = rootFile.listFiles();

        if (children != null) {
            for (File file : children) {
                Collection<? extends File> grandChildren = listFiles(file);
                files.addAll(grandChildren);
                files.add(file);
            }
        }

        return files;
    }

}
