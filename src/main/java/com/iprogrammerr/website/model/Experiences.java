package com.iprogrammerr.website.model;

import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Experiences {

    private final Path experiencesPath;
    private final String descriptionsPath;

    public Experiences(String experiencesPath, String descriptionsPath) {
        this.experiencesPath = Paths.get(experiencesPath);
        this.descriptionsPath = descriptionsPath;
    }

    public List<Experience> all() {
        try {
            JSONArray allExperiences = allJson();
            List<Experience> experiences = new ArrayList<>(allExperiences.length());
            return experiences;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(Files.readAllBytes(experiencesPath));
    }
}
