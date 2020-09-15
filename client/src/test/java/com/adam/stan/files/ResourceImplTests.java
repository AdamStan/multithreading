package com.adam.stan.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.adam.stan.storage.files.FileImpl;

public class ResourceImplTests {

    private static final String fileName = "file.xml";
    private static File file;

    @BeforeAll
    static void setUpMockFile() {
        file = Mockito.mock(File.class);
        Mockito.when(file.getName()).thenReturn(fileName);
        Mockito.when(file.getAbsolutePath())
                .thenReturn(System.getProperty("user.home") + File.separatorChar + fileName);
    }

    @Test
    void testRelativePath() {
        FileImpl implementation = new FileImpl(file, System.getProperty("user.home"));
        assertEquals(File.separator + fileName, implementation.relativePath());
    }
}
