package com.iprogrammerr.website.model;

import java.util.List;

public class CategorySkillsDetails {

    public final String category;
    public final List<Skill> skills;

    public CategorySkillsDetails(String category, List<Skill> skills) {
        this.category = category;
        this.skills = skills;
    }
}
