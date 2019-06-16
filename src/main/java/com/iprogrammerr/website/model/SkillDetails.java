package com.iprogrammerr.website.model;

import java.util.List;

public class SkillDetails {

    public final String category;
    public final List<String> items;
    public final String description;

    public SkillDetails(String category, List<String> items, String description) {
        this.category = category;
        this.items = items;
        this.description = description;
    }
}
