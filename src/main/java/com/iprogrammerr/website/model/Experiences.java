package com.iprogrammerr.website.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Experiences {

    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PLACE = "place";
    private static final String FUNCTION = "function";
    private static final String TECH_STACK = "techStack";
    private static final String SHORT_DESCRIPTION = "shortDescription";
    private static final String DESCRIPTION = "description";
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
            for (int i = 0; i < allExperiences.length(); i++) {
                int id = IdxId.fromIdx(i).id();
                experiences.add(fromJson(id, allExperiences.getJSONObject(i)));
            }
            return experiences;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONArray allJson() throws Exception {
        return new JSONArray(new String(Files.readAllBytes(experiencesPath)));
    }

    private Experience fromJson(int id, JSONObject json) {
        return new Experience(id, json.getString(START_DATE), json.getString(END_DATE), json.getString(PLACE),
            json.getString(FUNCTION), json.getString(SHORT_DESCRIPTION));
    }

    public ExperienceDetails experience(int id) {
        try {
            JSONArray experiences = allJson();
            int idx = IdxId.fromId(id, 1, experiences.length(), 1).idx();
            return fromJson(experiences.getJSONObject(idx));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ExperienceDetails fromJson(JSONObject json) throws Exception {
        JSONArray technologies = json.getJSONArray(TECH_STACK);
        List<String> technologyStack = new ArrayList<>(technologies.length());
        for (int i = 0; i < technologies.length(); i++) {
            technologyStack.add(technologies.getString(i));
        }
        String description = new String(Files.readAllBytes(Paths.get(descriptionsPath, json.getString(DESCRIPTION))));
        return new ExperienceDetails(json.getString(START_DATE), json.getString(END_DATE), json.getString(PLACE),
            json.getString(FUNCTION), technologyStack, description);
    }
}
