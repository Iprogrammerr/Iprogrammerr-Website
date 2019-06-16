package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;

public class SkillsRespondent implements HtmlRespondent {

    private static final String SKILLS = "skills";
    private final Views views;

    public SkillsRespondent(Views views) {
        this.views = views;
    }

    @Override
    public String path() {
        return SKILLS;
    }

    //TODO render path
    @Override
    public String response(HttpServletRequest request) {
        return views.view(SKILLS);
    }
}
