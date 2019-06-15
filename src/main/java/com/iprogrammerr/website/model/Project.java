package com.iprogrammerr.website.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Project {

    public final long id;
    public final String name;
    public final String goal;
    public final String description;
    public final List<String> links;
    public final State state;

    public Project(long id, String name, String goal, String description, List<String> links, State state) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        this.description = description;
        this.links = links;
        this.state = state;
    }

    public static Project fromJson(long id, JSONObject json) {
        String name = json.getString("name");
        String goal = json.getString("goal");
        String description = json.getString("description");
        List<String> links = new ArrayList<>();
        JSONArray linksArray = json.getJSONArray("links");
        for (int i = 0; i < linksArray.length(); i++) {
            links.add(linksArray.getString(i));
        }
        State state = State.valueOf(json.getString("state").toUpperCase());
        return new Project(id, name, goal, description, links, state);
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
