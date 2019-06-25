package com.iprogrammerr.website.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Skills {

    private static final String CATEGORY = "category";
    private static final String NAME = "name";
    private static final String SKILLS = "skills";
    private static final String DESCRIPTION = "description";
    private final Path skillsPath;

    public Skills(Path skillsPath) {
        this.skillsPath = skillsPath;
    }

    public Skills(String skillsPath) {
        this(Paths.get(skillsPath));
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(new String(Files.readAllBytes(skillsPath)));
    }

    public List<CategorySkills> all() {
        try {
            List<CategorySkills> skills = new ArrayList<>();
            JSONArray all = allJson();
            for (int i = 0; i < all.length(); i++) {
                skills.add(fromJson(all.getJSONObject(i)));
            }
            return skills;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CategorySkills fromJson(JSONObject json) {
        JSONArray elements = json.getJSONArray(SKILLS);
        List<String> items = new ArrayList<>(elements.length());
        for (int i = 0; i < elements.length(); i++) {
            items.add(elements.getJSONObject(i).getString(NAME));
        }
        return new CategorySkills(json.getString(CATEGORY), items);
    }

    public CategorySkillsDetails categorySkills(int id) {
        try {
            JSONArray all = allJson();
            JSONObject json = all.getJSONObject(IdxId.fromId(id, all.length()).idx());
            JSONArray skillsJson = json.getJSONArray(SKILLS);
            List<Skill> skills = new ArrayList<>(skillsJson.length());
            for (int i = 0; i < skillsJson.length(); i++) {
                JSONObject j = skillsJson.getJSONObject(i);
                skills.add(new Skill(j.getString(NAME), j.getString(DESCRIPTION)));
            }
            return new CategorySkillsDetails(json.getString(CATEGORY), skills);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
