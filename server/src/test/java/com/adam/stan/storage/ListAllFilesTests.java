package com.adam.stan.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.adam.stan.files.FileInfo;

public class ListAllFilesTests {

    private static File root;
    private static File folder1;
    private static File folder2;
    private static File folder3;
    private static final List<String> expectedRelativePaths = Arrays.asList(File.separator + "folder1",
            File.separator + "folder2", File.separator + "folder1" + File.separator + "file1.txt",
            File.separator + "folder1" + File.separator + "folder_nested" + File.separator + "file1.txt",
            File.separator + "folder1" + File.separator + "folder_nested",
            File.separator + "folder2" + File.separator + "file1.txt");

    @BeforeAll
    static void setUpFiles() throws IOException {
        root = new File("root");
        folder1 = new File("root" + File.separator + "folder1");
        folder2 = new File("root" + File.separator + "folder2");
        folder3 = new File("root" + File.separator + "folder1" + File.separator + "folder_nested");
        folder1.mkdirs();
        folder2.mkdirs();
        folder3.mkdirs();

        File file1 = new File("root" + File.separator + "folder1" + File.separator + "file1.txt");
        File file2 = new File("root" + File.separator + "folder2" + File.separator + "file1.txt");
        File file3 = new File(
                "root" + File.separator + "folder1" + File.separator + "folder_nested" + File.separator + "file1.txt");

        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
    }

    @AfterAll
    static void removeSetUpFiles() throws IOException {
        while(root.exists()) {
            Files.walk(root.toPath()).map(Path::toFile).forEachOrdered(File::delete);
        }
    }

    @Test
    void testPrepareAllFiles() {
        System.out.println(root.getAbsoluteFile());
        ListAllFiles lister = new ListAllFiles(root.getAbsolutePath());
        List<FileInfo> files = lister.prepareAllFiles();
        assertEquals(6, files.size());
        for (int i = 0; i < files.size(); i++) {
            assertEquals(expectedRelativePaths.get(i), files.get(i).getRelativePath());
        }
    }

}
