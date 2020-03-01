package com.iprogrammerr.website.file;

import org.json.JSONArray;

import java.io.File;

public class JsonArrayCache {

    private final FileCache source;
    private String previousJson;
    private JSONArray previous;

    public JsonArrayCache(FileCache source) {
        this.previousJson = "";
        this.source = source;
    }

    public JsonArrayCache(File source) {
        this(new FileCache(source));
    }

    public JSONArray content() {
        synchronized (this) {
            String json = source.textContent();
            if (!(previousJson.length() == json.length() && previousJson.equals(json))) {
                previousJson = json;
                previous = new JSONArray(previousJson);
            }
            return previous;
        }
    }

    public int contentLength() {
        try {
            return content().length();
        } catch (Exception e) {
            return 0;
        }
    }
}
