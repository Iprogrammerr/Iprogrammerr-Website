package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.Experiences;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;

public class ExperienceRespondent implements HtmlRespondent {

    private static final String EXPERIENCE = "experience";
    private final Views views;
    private final Experiences experiences;

    public ExperienceRespondent(Views views, Experiences experiences) {
        this.views = views;
        this.experiences = experiences;
    }

    //TODO render experience
    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue(-1);
        return views.view(EXPERIENCE);
    }

    @Override
    public String path() {
        return EXPERIENCE;
    }
}
