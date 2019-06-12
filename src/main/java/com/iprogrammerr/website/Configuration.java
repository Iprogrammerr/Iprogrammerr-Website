package com.iprogrammerr.website;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Configuration {

    private static final String PORT = "port";
    private final Properties source;

    public Configuration(Properties source) {
        this.source = source;
    }

    public static Configuration fromCmd(String... args) {
        String path;
        if (args.length == 0) {
            path = Configuration.class.getResource("/application.properties").getPath();
        } else {
            path = args[0];
        }
        try (InputStream is = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(is);
            return new Configuration(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getPort() {
        return Integer.parseInt(source.getProperty(PORT));
    }

    public String getResourcesPath() {
        String path;
        URL root = Configuration.class.getResource(".");
        if (root == null) {
            path = "";
        } else {
            String classPath = root.getPath();
            path = classPath.substring(0, classPath.indexOf("target")) + "src/main/resources";
        }
        return path;
    }
}
