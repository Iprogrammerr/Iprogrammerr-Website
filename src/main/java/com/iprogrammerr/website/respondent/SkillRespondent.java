package com.iprogrammerr.website.respondent;

import com.iprogrammerr.website.HtmlRespondent;
import com.iprogrammerr.website.model.SkillDetails;
import com.iprogrammerr.website.model.Skills;
import com.iprogrammerr.website.model.UrlParameter;
import com.iprogrammerr.website.view.Views;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SkillRespondent implements HtmlRespondent {

    private static final String SKILL = "skill";
    private static final String HAS_PREVIOUS = "hasPrevious";
    private static final String HAS_NEXT = "hasNext";
    private final Views views;
    private final Skills skills;

    public SkillRespondent(Views views, Skills skills) {
        this.views = views;
        this.skills = skills;
    }

    @Override
    public String path() {
        return SKILL;
    }

    @Override
    public String response(HttpServletRequest request) {
        int id = new UrlParameter(request.getRequestURI()).intValue(-1);
        SkillDetails skill = skills.skill(id);
        Map<String, Object> params = new HashMap<>();
        params.put(HAS_PREVIOUS, id > skills.firstId());
        params.put(HAS_NEXT, id < skills.lastId());
        params.put(SKILL, skill);
        return views.rendered(SKILL, params);
    }
}
