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
    private static final String DESCRIPTION = "description";
    private static final String WORK = "work";
    private static final String TYPE = "type";
    private final Path experiencesPath;

    public Experiences(String experiencesPath) {
        this.experiencesPath = Paths.get(experiencesPath);
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
            json.getString(FUNCTION), json.getString(DESCRIPTION));
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

    private ExperienceDetails fromJson(JSONObject json) {
        return new ExperienceDetails(json.getString(START_DATE), json.getString(END_DATE), json.getString(PLACE),
            json.getString(FUNCTION), fromJson(json.getJSONArray(WORK)));
    }

    private List<Software> fromJson(JSONArray json) {
        List<Software> software = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            software.add(new Software(j.getString(TYPE), j.getString(DESCRIPTION)));
        }
        return software;
    }
}
