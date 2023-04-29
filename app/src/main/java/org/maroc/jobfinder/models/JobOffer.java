package org.maroc.jobfinder.models;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class JobOffer implements Serializable {
    @Json(name = "id")
    private String id;
    @Json(name = "intitule")
    private String title;

    @Json(name = "dateCreation")
    private String description;
    @Json(name = "entreprise.logo")
    private String logoURL;

    public JobOffer(String id, String title, String company, String location, String description,String logoURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoURL=logoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setLogoUrl(String logoUrl) {
        this.logoURL = logoURL;
    }

    public String getLogoUrl() {
        return logoURL;
    }
}
