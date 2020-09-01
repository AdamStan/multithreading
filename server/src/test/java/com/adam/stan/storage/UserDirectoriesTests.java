package com.adam.stan.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class UserDirectoriesTests {
    @Test
    void testUserRootDirectory() {
        UserRootDirectory root = new UserRootDirectory("adam");
        String fullPath = root.getRootFile().getAbsolutePath();
        assertTrue(fullPath.endsWith(RootDirectory.NAME + File.separatorChar + "adam"));
    }
}
