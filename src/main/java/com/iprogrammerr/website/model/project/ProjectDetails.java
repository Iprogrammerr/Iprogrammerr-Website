package com.iprogrammerr.website.model.project;

import com.iprogrammerr.website.model.Link;

import java.util.List;

public class ProjectDetails {

    public final String name;
    public final String goal;
    public final String description;
    public final List<Link> links;

    public ProjectDetails(String name, String goal, String description, List<Link> links) {
        this.name = name;
        this.goal = goal;
        this.description = description;
        this.links = links;
    }
}
