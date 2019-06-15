package com.iprogrammerr.website.model;

import java.util.List;

public class ProjectDetails {

    public final String name;
    public final String goal;
    public final String description;
    public final List<String> links;
    public final State state;

    public ProjectDetails(String name, String goal, String description, List<String> links, State state) {
        this.name = name;
        this.goal = goal;
        this.description = description;
        this.links = links;
        this.state = state;
    }

    public enum State {
        COMPLETED, WORKING, DEAD;

        public String translated() {
            String translation;
            if (this == COMPLETED) {
                translation = "DONE";
            } else if (this == WORKING) {
                translation = "WIP";
            } else {
                translation = "RIP";
            }
            return translation;
        }
    }
}
