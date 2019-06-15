package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Experiences;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;

public class ExperienceRespondent implements HtmlRespondent {

    private static final String EXPERIENCE = "experience";
    private final HtmlViewsTemplates templates;
    private final Experiences experiences;

    public ExperienceRespondent(HtmlViewsTemplates templates, Experiences experiences) {
        this.templates = templates;
        this.experiences = experiences;
    }

    //TODO render experience
    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue(-1);
        return templates.rendered(EXPERIENCE);
    }

    @Override
    public String path() {
        return EXPERIENCE;
    }
}
