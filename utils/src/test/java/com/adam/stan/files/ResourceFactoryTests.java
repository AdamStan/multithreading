package com.adam.stan.files;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ResourceFactoryTests {

    private static java.io.File file;
    private static java.io.File directory;

    @BeforeAll
    static void setUp() {
        file = mock(java.io.File.class);
        when(file.isDirectory()).thenReturn(false);
        directory = mock(java.io.File.class);
        when(directory.isDirectory()).thenReturn(true);
    }

    @Test
    void testFile() {
        ResourceFactory factory = new ResourceFactory("");
        Resource ourFile = factory.getResource(file);
        assertTrue(ourFile instanceof File, "It is not a File class!");
    }

    @Test
    void testFolder() {
        ResourceFactory factory = new ResourceFactory("");
        Resource ourFile = factory.getResource(directory);
        assertTrue(ourFile instanceof Folder, "It is not a Folder class!");
    }
}
