package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.repository.ExperienceRepository;
import com.iprogrammerr.website.repository.ProjectRepository;
import com.iprogrammerr.website.repository.SkillRepository;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class WelcomeRespondent implements HtmlRespondent {

    private static final String WELCOME = "index";
    private final Views views;
    private final ExperienceRepository experienceRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;

    public WelcomeRespondent(Views views, ExperienceRepository experienceRepository,
            ProjectRepository projectRepository, SkillRepository skillRepository) {
        this.views = views;
        this.experienceRepository = experienceRepository;
        this.projectRepository = projectRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public String response(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put(TemplatesParams.EXPERIENCES, experienceRepository.all());
        params.put(TemplatesParams.PROJECTS, projectRepository.all());
        params.put(TemplatesParams.SKILLS, skillRepository.all());
        return views.rendered(WELCOME, params);
    }

    @Override
    public String path() {
        return WELCOME;
    }
}
