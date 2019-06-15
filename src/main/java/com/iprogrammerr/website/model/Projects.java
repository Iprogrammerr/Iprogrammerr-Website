package com.iprogrammerr.website.model;

import org.json.JSONArray;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Projects {

    private final List<Project> projects = new ArrayList<>();

    public void load(String path) {
        try {
            JSONArray items = new JSONArray(new String(Files.readAllBytes(Paths.get(path))));
            projects.clear();
            for (int i = 0; i < items.length(); i++) {
                projects.add(Project.fromJson(id(i), items.getJSONObject(i)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        load(new File(getClass().getResource("/projects.json").getPath()).getPath());
    }

    private int id(int idx) {
        return idx + 1;
    }

    public List<Project> all() {
        return projects;
    }

    public Optional<Project> project(int id) {
        Optional<Project> project;
        if (id > 0 && id <= projects.size()) {
            project = Optional.of(projects.get(id - 1));
        } else {
            project = Optional.empty();
        }
        return project;
    }
}
