package org.maroc.jobfinder.models;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.moshi.Json;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

import java.io.Serializable;

@Entity(tableName = "JobOffer")
public class JobOffer implements Serializable {

    @PrimaryKey
    @NonNull
    @Json(name = "id")
    private String id;

    @ColumnInfo(name = "title")
    @Json(name = "intitule")
    private String title;

    @ColumnInfo(name = "description")
    @Json(name = "dateCreation")
    private String description;

    @ColumnInfo(name = "logo_url")
    @Json(name = "entreprise.logo")
    private String logoURL;

    @ColumnInfo(name = "offre_url")
    @Json(name = "origineOffre.urlOrigine")
    private String offreURL;

    public JobOffer() {
        // Empty constructor required by Room
    }

    public JobOffer(String id, String title, String company, String location, String description,String logoURL,String offreURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoURL=logoURL;
        this.offreURL=offreURL;
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


    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setOffreURL(String offreURL) {
        this.offreURL = offreURL;
    }

    public String getOffreURL() {
        return offreURL;
    }
}
