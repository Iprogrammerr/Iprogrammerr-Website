package com.iprogrammerr.website.model;

public class Experience {

    public final long id;
    public final String startDate;
    public final String endDate;
    public final String place;
    public final String function;
    public final String description;

    public Experience(long id, String startDate, String endDate, String place, String function,
        String description) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.function = function;
        this.description = description;
    }
}
