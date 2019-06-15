package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;

public class SkillsRespondent implements HtmlRespondent {

    private static final String SKILLS = "skills";
    private final HtmlViewsTemplates templates;

    public SkillsRespondent(HtmlViewsTemplates templates) {
        this.templates = templates;
    }

    @Override
    public String path() {
        return SKILLS;
    }

    //TODO render path
    @Override
    public String response(HttpServletRequest request) {
        return templates.rendered(SKILLS);
    }
}
