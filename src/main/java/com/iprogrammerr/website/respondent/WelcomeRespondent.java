package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.view.HtmlViewsTemplates;

import javax.servlet.http.HttpServletRequest;

public class WelcomeRespondent implements HtmlRespondent {

    private static final String TEMPLATE = "index";
    private final HtmlViewsTemplates templates;

    public WelcomeRespondent(HtmlViewsTemplates templates) {
        this.templates = templates;
    }

    @Override
    public String response(HttpServletRequest request) {
        return templates.rendered(TEMPLATE);
    }
}
