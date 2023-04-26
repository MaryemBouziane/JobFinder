package org.maroc.jobfinder.models;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JobOffer {
    private String title;
    private String companyName;
    private String logoUrl;

    public JobOffer(String title, String companyName, String logoUrl) {
        this.title = title;
        this.companyName = companyName;
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public static List<JobOffer> fromJsonArray(String json) {
        List<JobOffer> jobOffers = new ArrayList<>();

        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            String title = jsonObject.get("title").getAsString();
            String companyName = jsonObject.get("companyName").getAsString();
            String logoUrl = jsonObject.get("logoUrl").getAsString();

            jobOffers.add(new JobOffer(title, companyName, logoUrl));
        }

        return jobOffers;
    }
}
