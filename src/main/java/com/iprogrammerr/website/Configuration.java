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

    public static Configuration fromCmd(String... args) throws Exception {
        InputStream is;
        if (args.length > 0) {
            is = new FileInputStream(args[0]);
        } else {
            is = Configuration.class.getResourceAsStream("/application.properties");
        }
        try {
            Properties properties = new Properties();
            properties.load(is);
            return new Configuration(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            is.close();
        }
    }

    public int getPort() {
        return Integer.parseInt(source.getProperty(PORT));
    }

    private File getResources() {
        return new File(source.getProperty(RESOURCES_PATH, ""));
    }

    public File getDatabase() {
        return new File(getResources(), "database");
    }

    public File getPublicResources() {
        return new File(getResources(), "public");
    }

    public File getTemplates() {
        return new File(getResources(), "template");
    }

    public boolean shouldCacheStaticResources() {
        return Boolean.parseBoolean(source.getProperty(CACHE_STATIC_RESOURCES));
    }

    public List<Mapping> getMappings() throws Exception {
        String json = new String(
                Files.readAllBytes(Paths.get(getResources().getPath() + File.separator + "mappings.json")));
        JSONArray all = new JSONArray(json);
        List<Mapping> resources = new ArrayList<>(all.length());
        for (int i = 0; i < all.length(); i++) {
            resources.add(Mapping.fromJson(all.getJSONObject(i)));
        }
        return resources;
    }
}
