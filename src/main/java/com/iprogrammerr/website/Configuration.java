package com.iprogrammerr.website;

import com.iprogrammerr.website.model.Mapping;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuration {

    private static final String PORT = "port";
    private static final String RESOURCES_PATH = "resourcesPath";
    private static final String CACHE_STATIC_RESOURCES = "cacheStaticResources";
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

    public File getResources() {
        String path = source.getProperty(RESOURCES_PATH, "");
        if (path.isEmpty()) {
            String classPath = Configuration.class.getResource(".").getPath();
            path = classPath.substring(0, classPath.indexOf("target")) + "src/main/resources";
        }
        return new File(path);
    }

    public boolean shouldCacheStaticResources() {
        return Boolean.parseBoolean(source.getProperty(CACHE_STATIC_RESOURCES));
    }

    public List<Mapping> getMappings() throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(getResources().getPath() +
            File.separator + "mappings.json")));
        JSONArray all = new JSONArray(json);
        List<Mapping> resources = new ArrayList<>(all.length());
        for (int i = 0; i < all.length(); i++) {
            resources.add(Mapping.fromJson(all.getJSONObject(i)));
        }
        return resources;
    }
}
