package com.iprogrammerr.website.model;

public class UrlParameter {

    private final String url;

    public UrlParameter(String url) {
        this.url = url;
    }

    public int intValue(int defaultValue) {
        int value;
        try {
            String[] parts = parts();
            value = Integer.parseInt(parts[parts.length - 1].trim());
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public int intValue() {
        return intValue(-1);
    }

    private String[] parts() {
        return url.split("/");
    }
}
