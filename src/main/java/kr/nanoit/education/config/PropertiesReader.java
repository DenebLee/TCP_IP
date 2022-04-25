package kr.nanoit.education.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesReader {

    private PropertiesReader() {
    }

    public static Properties read(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(path)));

        String id = properties.getProperty("id");
        if (id == null || id.length() <= 0 || id.trim().length() <= 0) {
            throw new IOException("validation failed properties.id");
        }

        String encryptKey = properties.getProperty("encryptKey");
        if (encryptKey == null || encryptKey.length() <= 0 || encryptKey.trim().length() <= 0) {
            throw new IOException("validation failed properties.encryptKey");
        }
        return properties;


    }
}