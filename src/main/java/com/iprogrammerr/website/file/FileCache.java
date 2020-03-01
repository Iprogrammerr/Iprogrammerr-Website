package com.iprogrammerr.website.file;

import java.io.File;
import java.nio.file.Files;

public class FileCache {

    private final File source;
    private byte[] rawContent;
    private String textContent;
    private long lastModified;

    public FileCache(File source) {
        this.source = source;
        this.textContent = "";
        this.lastModified = -1;
    }

    public byte[] rawContent() {
        readIf();
        return rawContent;
    }

    private void readIf() {
        try {
            if (source.lastModified() > lastModified) {
                rawContent = Files.readAllBytes(source.toPath());
                lastModified = source.lastModified();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String textContent() {
        long previousLastModified = lastModified;
        readIf();
        if (previousLastModified != lastModified || textContent.isEmpty()) {
            textContent = new String(rawContent);
        }
        return textContent;
    }
}
