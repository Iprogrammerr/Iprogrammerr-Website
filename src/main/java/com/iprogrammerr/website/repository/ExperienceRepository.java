package com.iprogrammerr.website.repository;

import com.iprogrammerr.website.file.JsonArrayCache;
import com.iprogrammerr.website.model.Experience;
import com.iprogrammerr.website.model.ExperienceDetails;
import com.iprogrammerr.website.model.IdxId;
import com.iprogrammerr.website.model.Software;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExperienceRepository extends JsonRepository {

    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PLACE = "place";
    private static final String FUNCTION = "function";
    private static final String DESCRIPTION = "description";
    private static final String WORK = "work";
    private static final String TYPE = "type";

    public ExperienceRepository(File experiencesFile) {
        super(new JsonArrayCache(experiencesFile));
    }

    public List<Experience> all() {
        try {
            JSONArray allExperiences = cache.content();
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

    private Experience fromJson(int id, JSONObject json) {
        return new Experience(id, json.getString(START_DATE), json.getString(END_DATE), json.getString(PLACE),
                json.getString(FUNCTION), json.getString(DESCRIPTION));
    }

    public ExperienceDetails experience(int id) {
        try {
            JSONArray experiences = cache.content();
            int idx = IdxId.fromId(id, experiences.length()).idx();
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
