package com.iprogrammerr.website.model.experience;

import com.iprogrammerr.website.model.Software;

import java.util.List;

public class ExperienceDetails {

    public final String startDate;
    public final String endDate;
    public final String place;
    public final String function;
    public final List<Software> work;

    public ExperienceDetails(String startDate, String endDate, String place, String function, List<Software> work) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.function = function;
        this.work = work;
    }
}
