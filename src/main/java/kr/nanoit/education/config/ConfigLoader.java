package kr.nanoit.education.config;

import java.io.File;

public final class ConfigLoader {

    private static final String USER_DIR = System.getProperty("user.dir");

    private ConfigLoader() {
    }

    public static File load() {
        return new File(USER_DIR.concat("/../test.properties"));
    }
}
