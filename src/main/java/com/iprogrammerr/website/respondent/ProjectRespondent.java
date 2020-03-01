package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.repository.ProjectRepository;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ProjectRespondent implements HtmlRespondent {

    private static final String PROJECT = "project";
    private final Views views;
    private final ProjectRepository projectRepository;

    public ProjectRespondent(Views views, ProjectRepository projectRepository) {
        this.views = views;
        this.projectRepository = projectRepository;
    }

    @Override
    public String path() {
        return PROJECT;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue();
        Map<String, Object> params = new HashMap<>();
        params.put(TemplatesParams.HAS_PREVIOUS, id > projectRepository.firstId());
        params.put(TemplatesParams.HAS_NEXT, id < projectRepository.lastId());
        params.put(TemplatesParams.PROJECT, projectRepository.project(id));
        return views.rendered(PROJECT, params);
    }
}
