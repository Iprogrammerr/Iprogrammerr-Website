package com.iprogrammerr.website;

import com.iprogrammerr.website.respondent.WelcomeRespondent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private static final String SEPARATOR = "/";
    private static final String HTML_CONTENT_TYPE = "text/html";
    private final WelcomeRespondent welcomeRespondent;
    private final HtmlRespondent[] respondents;

    public DispatcherServlet(WelcomeRespondent welcomeRespondent, HtmlRespondent... respondents) {
        this.welcomeRespondent = welcomeRespondent;
        this.respondents = respondents;
    }

    //TODO exception page
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String response = response(req.getRequestURI(), req);
        resp.setContentType(HTML_CONTENT_TYPE);
        if (!response.isEmpty()) {
            resp.getWriter().write(response);
        }
    }

    private String response(String url, HttpServletRequest req) {
        String response;
        if (url.equals(SEPARATOR) || url.startsWith("/index.html")) {
            response = welcomeRespondent.response(req);
        } else {
            response = "";
            for (HtmlRespondent r : respondents) {
                String rPath = "/" + r.path();
                if (url.startsWith(rPath)) {
                    response = r.response(req);
                    break;
                }
            }
        }
        return response;
    }
}
