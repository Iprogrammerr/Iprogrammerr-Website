package com.iprogrammerr.website.view;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class HtmlViews implements Views {

    private static final String EXTENSION = ".html";
    private final File root;
    private final ITemplateEngine engine;

    public HtmlViews(File root, ITemplateEngine engine) {
        this.root = root;
        this.engine = engine;
    }

    @Override
    public String view(String name) {
        try {
            return new String(Files.readAllBytes(Paths.get(root.getPath(), name + EXTENSION)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String rendered(String name, Map<String, Object> params) {
        Context context = new Context();
        context.setVariables(params);
        return engine.process(name + EXTENSION, context);
    }
}
