package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;

public class AboutRespondent implements HtmlRespondent {

    private static final String ABOUT = "about";

    private final Views views;

    public AboutRespondent(Views views) {
        this.views = views;
    }

    @Override
    public String response(HttpServletRequest request) {
        return views.view(ABOUT);
    }

    @Override
    public String path() {
        return ABOUT;
    }
}
