package com.iprogrammerr.website;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Respondent {
    void respond(HttpServletRequest req, HttpServletResponse res);
}
