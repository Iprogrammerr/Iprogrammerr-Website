package com.iprogrammerr.website.model;

import org.json.JSONObject;

public class Mapping {

    public final String context;
    public final String path;
    public final String welcomeFile;

    public Mapping(String context, String path, String welcomeFile) {
        this.context = context;
        this.path = path;
        this.welcomeFile = welcomeFile;
    }

    public static Mapping fromJson(JSONObject json) {
        return new Mapping(json.getString("context"), json.getString("path"), json.getString("welcomeFile"));
    }
}
