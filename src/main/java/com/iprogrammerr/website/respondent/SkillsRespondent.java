package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.repository.SkillRepository;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SkillsRespondent implements HtmlRespondent {

    private static final String SKILLS = "skills";
    private final Views views;
    private final SkillRepository skillRepository;

    public SkillsRespondent(Views views, SkillRepository skillRepository) {
        this.views = views;
        this.skillRepository = skillRepository;
    }

    @Override
    public String path() {
        return SKILLS;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue();
        Map<String, Object> params = new HashMap<>();
        params.put(TemplatesParams.HAS_PREVIOUS, id > skillRepository.firstId());
        params.put(TemplatesParams.HAS_NEXT, id < skillRepository.lastId());
        params.put(TemplatesParams.SKILLS, skillRepository.skillsOfCategory(id));
        return views.rendered(SKILLS, params);
    }
}
