package com.iprogrammerr.website;

import com.iprogrammerr.website.respondent.ErrorRespondent;
import com.iprogrammerr.website.respondent.WelcomeRespondent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

public class DispatcherServlet extends HttpServlet {

    private static final String SEPARATOR = "/";
    private static final String HTML_CONTENT_TYPE = "text/html";
    private static final String LOCATION_HEADER = "Location";
    private static final String ICON_REQUEST = "/favicon";
    private static final String ICON_REDIRECT = "/resources" + ICON_REQUEST + ".ico";
    private final WelcomeRespondent welcomeRespondent;
    private final ErrorRespondent errorRespondent;
    private final HtmlRespondent[] respondents;

    public DispatcherServlet(WelcomeRespondent welcomeRespondent, ErrorRespondent errorRespondent,
        HtmlRespondent... respondents) {
        this.welcomeRespondent = welcomeRespondent;
        this.errorRespondent = errorRespondent;
        this.respondents = respondents;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        String response = response(url, req);
        if (!response.isEmpty()) {
            resp.setContentType(HTML_CONTENT_TYPE);
            resp.getWriter().write(response);
        } else {
            String redirect = url.startsWith(ICON_REQUEST) ? ICON_REDIRECT : SEPARATOR;
            resp.setHeader(LOCATION_HEADER, redirect);
            resp.setStatus(HttpURLConnection.HTTP_SEE_OTHER);
        }

    }

    private String response(String url, HttpServletRequest req) {
        String response;
        try {
            if (url.equals(SEPARATOR)) {
                response = welcomeRespondent.response(req);
            } else {
                response = "";
                for (HtmlRespondent r : respondents) {
                    String rPath = SEPARATOR + r.path();
                    if (url.startsWith(rPath)) {
                        response = r.response(req);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            response = errorRespondent.response();
        }
        return response;
    }
}
