package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Projects;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class WelcomeRespondent implements HtmlRespondent {

    private static final String TEMPLATE = "index";
    private static final String PROJECTS_TEMPLATE = "projects";
    private final HtmlViewsTemplates templates;
    private final Projects projects;

    public WelcomeRespondent(HtmlViewsTemplates templates, Projects projects) {
        this.templates = templates;
        this.projects = projects;
    }

    @Override
    public String response(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put(PROJECTS_TEMPLATE, projects.all());
        return templates.rendered(TEMPLATE, params);
    }
}
