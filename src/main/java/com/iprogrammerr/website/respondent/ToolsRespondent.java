package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;

public class ToolsRespondent implements HtmlRespondent {

    private static final String TOOLS = "tools";
    private static final String DEFAULT_TOOL = "comparator";
    private final Views views;

    public ToolsRespondent(Views views) {
        this.views = views;
    }

    @Override
    public String path() {
        return TOOLS;
    }

    @Override
    public String response(HttpServletRequest request) {
        //TODO load tools from json
        String tool = new UrlParameter(request.getRequestURI()).stringValue(DEFAULT_TOOL);
        return views.view(DEFAULT_TOOL);
    }
}
