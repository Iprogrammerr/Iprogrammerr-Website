package com.iprogrammerr.website.model;

import java.util.List;

public class Skill {

    public final String category;
    public final List<String> items;

    public Skill(String category, List<String> items) {
        this.category = category;
        this.items = items;
    }
}
