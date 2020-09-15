package com.adam.stan.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.adam.stan.security.User;

public class UserDirectoriesTests {
    @Test
    void testUserRootDirectory() {
        UserRootDirectory root = new UserRootDirectory(new User("adam"));
        String fullPath = root.getRootFile().getAbsolutePath();
        assertTrue(fullPath.endsWith(RootDirectory.NAME + File.separatorChar + "adam"));
    }
}
