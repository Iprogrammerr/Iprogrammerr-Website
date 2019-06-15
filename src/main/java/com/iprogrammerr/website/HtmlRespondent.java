package com.iprogrammerr.website;

import javax.servlet.http.HttpServletRequest;

public interface HtmlRespondent {

    String path();

    String response(HttpServletRequest request);
}
