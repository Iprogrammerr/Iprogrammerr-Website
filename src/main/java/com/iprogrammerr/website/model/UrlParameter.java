package com.iprogrammerr.website.model;

public class UrlParameter {

    private final String url;

    public UrlParameter(String url) {
        this.url = url;
    }

    public int intValue(int defaultValue) {
        int value;
        try {
            value = Integer.parseInt(stringValue(String.valueOf(defaultValue)));
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public String stringValue(String defaultValue) {
        String[] parts = parts();
        return parts.length == 0 ? defaultValue : parts[parts.length - 1].trim();
    }

    public int intValue() {
        return intValue(-1);
    }

    private String[] parts() {
        return url.split("/");
    }
}
