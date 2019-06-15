package com.iprogrammerr.website.model;

public class Experience {

    public final long start;
    public final long end;
    public final String place;
    public final String function;
    public final String shortDescription;

    public Experience(long start, long end, String place, String function, String shortDescription) {
        this.start = start;
        this.end = end;
        this.place = place;
        this.function = function;
        this.shortDescription = shortDescription;
    }
}
