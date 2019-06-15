package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Projects;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;

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

    //TODO render project
    @Override
    public String response(HttpServletRequest request) {
        return templates.rendered(PROJECT);
    }
}
