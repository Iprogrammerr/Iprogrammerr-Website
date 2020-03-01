package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.repository.ExperienceRepository;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ExperienceRespondent implements HtmlRespondent {

    private static final String EXPERIENCE = "experience";
    private final Views views;
    private final ExperienceRepository experienceRepository;

    public ExperienceRespondent(Views views, ExperienceRepository experienceRepository) {
        this.views = views;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue();
        Map<String, Object> params = new HashMap<>();
        params.put(TemplatesParams.HAS_PREVIOUS, id > experienceRepository.firstId());
        params.put(TemplatesParams.HAS_NEXT, id < experienceRepository.lastId());
        params.put(TemplatesParams.EXPERIENCE, experienceRepository.experience(id));
        return views.rendered(EXPERIENCE, params);
    }

    @Override
    public String path() {
        return EXPERIENCE;
    }
}
