package com.iprogrammerr.website;

import com.iprogrammerr.website.respondent.WelcomeRespondent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private static final String ROOT_URL = "/";
    private static final String HTML_CONTENT_TYPE = "text/html";
    private final WelcomeRespondent welcomeRespondent;

    public DispatcherServlet(WelcomeRespondent welcomeRespondent) {
        this.welcomeRespondent = welcomeRespondent;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String url = req.getRequestURI();
        String response = "";
        if (url.equals(ROOT_URL) || url.startsWith("/index.html")) {
            response = welcomeRespondent.response(req);
        }
        resp.setContentType(HTML_CONTENT_TYPE);
        if (!response.isEmpty()) {
            resp.getWriter().write(response);
        }
    }
}
