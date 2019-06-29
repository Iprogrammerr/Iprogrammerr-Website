package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.view.Views;

public class ErrorRespondent {

    private static final String ERROR = "error";
    private final Views views;

    public ErrorRespondent(Views views) {
        this.views = views;
    }

    public String response() {
        return views.view(ERROR);
    }
}
