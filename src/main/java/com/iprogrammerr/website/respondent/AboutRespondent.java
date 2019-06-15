package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;

public class AboutRespondent implements HtmlRespondent {

    private static final String ABOUT = "about";

    private final HtmlViewsTemplates templates;

    public AboutRespondent(HtmlViewsTemplates templates) {
        this.templates = templates;
    }

    @Override
    public String response(HttpServletRequest request) {
        return templates.rendered(ABOUT);
    }

    @Override
    public String path() {
        return ABOUT;
    }
}
