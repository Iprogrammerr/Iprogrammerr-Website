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
    private static final String ITEMS = "items";
    private static final String DESCRIPTION = "description";
    private final Path skillsPath;

    public Skills(String skillsPath) {
        this.skillsPath = Paths.get(skillsPath);
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(new String(Files.readAllBytes(skillsPath)));
    }

    public List<Skill> all() {
        try {
            List<Skill> skills = new ArrayList<>();
            JSONArray all = allJson();
            for (int i = 0; i < all.length(); i++) {
                skills.add(fromJson(all.getJSONObject(i)));
            }
            return skills;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Skill fromJson(JSONObject json) {
        JSONArray elements = json.getJSONArray(ITEMS);
        List<String> items = new ArrayList<>(elements.length());
        for (int i = 0; i < elements.length(); i++) {
            items.add(elements.getString(i));
        }
        return new Skill(json.getString(CATEGORY), items);
    }

    public SkillDetails skill(int id) {
        try {
            JSONArray all = allJson();
            JSONObject json = all.getJSONObject(IdxId.fromId(id, all.length()).idx());
            Skill skill = fromJson(json);
            return new SkillDetails(skill.category, skill.items, json.getString(DESCRIPTION));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int firstId() {
        return 1;
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
