package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Projects;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ProjectRespondent implements HtmlRespondent {

    private static final String PROJECT = "project";
    private final HtmlViewsTemplates templates;
    private final Projects projects;

    public ProjectRespondent(HtmlViewsTemplates templates, Projects projects) {
        this.templates = templates;
        this.projects = projects;
    }

    @Override
    public String path() {
        return PROJECT;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue(-1);
        Map<String, Object> params = new HashMap<>();
        params.put(PROJECT, projects.project(id));
        return templates.rendered(PROJECT, params);
    }
}
