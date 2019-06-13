package com.iprogrammerr.website.view;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlViews {

    private static final String EXTENSION = ".html";
    private final File root;

    public HtmlViews(File root) {
        this.root = root;
    }

    public String view(String name) {
        try {
            return new String(Files.readAllBytes(Paths.get(root.getPath(), name + EXTENSION)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
