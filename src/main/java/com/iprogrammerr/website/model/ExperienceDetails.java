package com.iprogrammerr.website.model;

import java.util.List;

public class ExperienceDetails {

    public final String startDate;
    public final String endDate;
    public final String place;
    public final String function;
    public final List<String> techStack;
    public final String description;

    public ExperienceDetails(String startDate, String endDate, String place, String function,
        List<String> techStack, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.function = function;
        this.techStack = techStack;
        this.description = description;
    }
}
