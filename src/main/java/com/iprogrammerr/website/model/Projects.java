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
    private static final String URL = "url";
    private final Path projectsPath;

    public Projects(Path projectsPath) {
        this.projectsPath = projectsPath;
    }

    public Projects(String projectsPath) {
        this(Paths.get(projectsPath));
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
            int idx = IdxId.fromId(id, projects.length()).idx();
            return fromJson(projects.getJSONObject(idx));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ProjectDetails fromJson(JSONObject json) {
        String name = json.getString(NAME);
        String goal = json.getString(GOAL);
        List<Link> links = new ArrayList<>();
        JSONArray linksJson = json.getJSONArray(LINKS);
        for (int i = 0; i < linksJson.length(); i++) {
            JSONObject lj = linksJson.getJSONObject(i);
            links.add(new Link(lj.getString(URL), lj.getString(NAME)));
        }
        return new ProjectDetails(name, goal, json.getString(DESCRIPTION), links);
    }

    public int firstId() {
        return IdxId.FIRST_ID;
    }

    public int lastId() {
        int last;
        try {
            last = allJson().length();
        } catch (Exception e) {
            last = 0;
        }
        return last;
    }
}
