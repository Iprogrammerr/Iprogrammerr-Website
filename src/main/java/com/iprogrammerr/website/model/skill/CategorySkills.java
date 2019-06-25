package com.iprogrammerr.website.model.skill;

import java.util.List;

public class CategorySkills {

    public final String category;
    public final List<String> skills;

    public CategorySkills(String category, List<String> skills) {
        this.category = category;
        this.skills = skills;
    }
}
