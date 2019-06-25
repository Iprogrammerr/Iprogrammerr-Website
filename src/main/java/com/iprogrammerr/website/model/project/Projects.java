package com.iprogrammerr.website.model.project;

import com.iprogrammerr.website.model.IdxId;
import com.iprogrammerr.website.model.JsonArrayCache;
import com.iprogrammerr.website.model.Link;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Projects {

    private static final String NAME = "name";
    private static final String GOAL = "goal";
    private static final String DESCRIPTION = "description";
    private static final String LINKS = "links";
    private static final String URL = "url";
    private final JsonArrayCache cache;

    public Projects(File projectsFile) {
        cache = new JsonArrayCache(projectsFile);
    }

    public List<Project> all() {
        try {
            List<Project> projects = new ArrayList<>();
            JSONArray all = cache.content();
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
            JSONArray projects = cache.content();
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
            last = cache.content().length();
        } catch (Exception e) {
            last = 0;
        }
        return last;
    }
}
