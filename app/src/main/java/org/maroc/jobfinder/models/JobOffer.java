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

    @ColumnInfo(name = "dateCreation")
    @Json(name = "dateCreation")
    private String dateCreation;

    @ColumnInfo(name = "logo_url")
    @Json(name = "entreprise.logo")
    private String logoURL;

    @ColumnInfo(name = "offre_url")
    @Json(name = "origineOffre.urlOrigine")
    private String offreURL;

    @ColumnInfo(name = "typeContrat")
    @Json(name = "typeContrat")
    private String typeContrat;

    @ColumnInfo(name = "nombrePostes")
    @Json(name = "nombrePostes")
    private Integer nombrePostes;

    @ColumnInfo(name = "offresManqueCandidats")
    @Json(name = "offresManqueCandidats")
    private boolean offresManqueCandidats;

    @ColumnInfo(name = "entreprise_nom")
    @Json(name = "entreprise.nom")
    private String entrepriseNom;

    @ColumnInfo(name = "experienceExige")
    @Json(name = "experienceExige")
    private String experienceExige;


    @ColumnInfo(name = "description")
    @Json(name = "description")
    private String description;

    public JobOffer() {
        // Empty constructor required by Room
    }

    public JobOffer(String id, String title, String experienceExige, String description, String dateCreation,String logoURL,String offreURL,String typeContrat,Integer nombrePostes, boolean offresManqueCandidats,String  entrepriseNom) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.experienceExige=experienceExige;
        this.dateCreation = dateCreation;
        this.logoURL=logoURL;
        this.offreURL=offreURL;
        this.typeContrat=typeContrat;
        this.nombrePostes=nombrePostes;
        this.offresManqueCandidats=offresManqueCandidats;
        this.entrepriseNom=entrepriseNom;

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
    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Integer getNombrePostes() {
        return nombrePostes;
    }

    public void setNombrePostes(Integer nombrePostes) {
        this.nombrePostes = nombrePostes;
    }

    public boolean isOffresManqueCandidats() {
        return offresManqueCandidats;
    }

    public void setOffresManqueCandidats(boolean offresManqueCandidats) {
        this.offresManqueCandidats = offresManqueCandidats;
    }
    public String getEntrepriseNom() {
        return entrepriseNom;
    }

    public void setEntrepriseNom(String entrepriseNom) {
        this.entrepriseNom = entrepriseNom;
    }
    public String getExperienceExige() {
        return experienceExige;
    }

    public void setExperienceExige(String experienceExige) {
        this.experienceExige = experienceExige;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}
