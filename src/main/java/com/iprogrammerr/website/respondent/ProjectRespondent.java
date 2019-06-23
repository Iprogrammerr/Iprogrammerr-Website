package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Projects;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ProjectRespondent implements HtmlRespondent {

    private static final String PROJECT = "project";
    private final Views views;
    private final Projects projects;

    public ProjectRespondent(Views views, Projects projects) {
        this.views = views;
        this.projects = projects;
    }

    @Override
    public String path() {
        return PROJECT;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue();
        Map<String, Object> params = new HashMap<>();
        params.put(PROJECT, projects.project(id));
        return views.rendered(PROJECT, params);
    }
}
