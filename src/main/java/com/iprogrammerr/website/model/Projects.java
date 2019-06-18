package com.iprogrammerr.website.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Projects {

    private static final String NAME = "name";
    private static final String GOAL = "goal";
    private static final String DESCRIPTION = "description";
    private static final String LINKS = "links";
    private final Path projectsPath;

    public Projects(String projectsPath) {
        this.projectsPath = Paths.get(projectsPath);
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(new String(Files.readAllBytes(projectsPath)));
    }

    public List<Project> all() {
        try {
            List<Project> projects = new ArrayList<>();
            JSONArray all = allJson();
            for (int i = 0; i < all.length(); i++) {
                int id = IdxId.fromIdx(i).id();
                projects.add(new Project(id, all.getJSONObject(i).getString(NAME)));
            }
            return projects;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProjectDetails project(int id) {
        try {
            JSONArray projects = allJson();
            int idx = IdxId.fromId(id, 1, projects.length(), 1).idx();
            return fromJson(projects.getJSONObject(idx));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ProjectDetails fromJson(JSONObject json) {
        String name = json.getString(NAME);
        String goal = json.getString(GOAL);
        List<String> links = new ArrayList<>();
        JSONArray linksArray = json.getJSONArray(LINKS);
        for (int i = 0; i < linksArray.length(); i++) {
            links.add(linksArray.getString(i));
        }
        return new ProjectDetails(name, goal, json.getString(DESCRIPTION), links);
    }
}
