package com.iprogrammerr.website.repository;

import com.iprogrammerr.website.file.JsonArrayCache;
import com.iprogrammerr.website.model.CategorySkills;
import com.iprogrammerr.website.model.CategorySkillsDetails;
import com.iprogrammerr.website.model.IdxId;
import com.iprogrammerr.website.model.Skill;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository extends JsonRepository {

    private static final String CATEGORY = "category";
    private static final String NAME = "name";
    private static final String SKILLS = "skills";
    private static final String DESCRIPTION = "description";

    public SkillRepository(File skillsFile) {
        super(new JsonArrayCache(skillsFile));
    }

    public List<CategorySkills> all() {
        try {
            List<CategorySkills> skills = new ArrayList<>();
            JSONArray all = cache.content();
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

    public CategorySkillsDetails skillsOfCategory(int id) {
        try {
            JSONArray all = cache.content();
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
}
