package com.iprogrammerr.website.view;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class HtmlViewsTemplates {

    private static final Map<String, Object> EMPTY = new HashMap<>();
    private final ITemplateEngine engine;

    public HtmlViewsTemplates(ITemplateEngine engine) {
        this.engine = engine;
    }

    public String rendered(String name, Map<String, Object> params) {
        Context context = new Context();
        context.setVariables(params);
        return engine.process(name + ".html", context);
    }

    public String rendered(String name) {
        return rendered(name, EMPTY);
    }
}
