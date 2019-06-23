package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Experiences;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ExperienceRespondent implements HtmlRespondent {

    private static final String EXPERIENCE = "experience";
    private final Views views;
    private final Experiences experiences;

    public ExperienceRespondent(Views views, Experiences experiences) {
        this.views = views;
        this.experiences = experiences;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue();
        Map<String, Object> params = new HashMap<>();
        params.put(EXPERIENCE, experiences.experience(id));
        return views.rendered(EXPERIENCE, params);
    }

    @Override
    public String path() {
        return EXPERIENCE;
    }
}
