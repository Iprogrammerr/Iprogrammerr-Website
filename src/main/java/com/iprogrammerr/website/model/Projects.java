package com.iprogrammerr.website.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
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
    private static final String STATE = "state";
    private final Path projectsPath;
    private final Path descriptionsPath;

    public Projects(Path projectsPath, Path descriptionsPath) {
        this.projectsPath = projectsPath;
        this.descriptionsPath = descriptionsPath;
    }

    public Projects() {
        this(new File(Project.class.getResource("/database/projects.json").getPath()).toPath(),
            new File(Project.class.getResource("/database/description").getPath()).toPath());
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(new String(Files.readAllBytes(projectsPath)));
    }

    private int id(int idx) {
        return idx + 1;
    }

    public List<Project> all() {
        List<Project> projects = new ArrayList<>();
        try {
            JSONArray all = allJson();
            for (int i = 0; i < all.length(); i++) {
                projects.add(new Project(id(i), all.getJSONObject(i).getString(NAME)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    public ProjectDetails project(int id) {
        try {
            JSONArray projects = allJson();
            int idx;
            if (id > 0 && id <= projects.length()) {
                idx = id - 1;
            } else {
                idx = 0;
            }
            return fromJson(projects.getJSONObject(idx));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ProjectDetails fromJson(JSONObject json) throws Exception {
        String name = json.getString(NAME);
        String goal = json.getString(GOAL);
        String description = new String(Files.readAllBytes(Paths.get(descriptionsPath.toString(),
            json.getString(DESCRIPTION))));
        List<String> links = new ArrayList<>();
        JSONArray linksArray = json.getJSONArray(LINKS);
        for (int i = 0; i < linksArray.length(); i++) {
            links.add(linksArray.getString(i));
        }
        ProjectDetails.State state = ProjectDetails.State.valueOf(json.getString(STATE).toUpperCase());
        return new ProjectDetails(name, goal, description, links, state);
    }
}
