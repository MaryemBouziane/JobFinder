package org.maroc.jobfinder.api;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.List;

public class JobOffersResponse {

    @Json(name = "resultats")
    private List<JobOffer> jobOffers;
    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
    @Override
    public String toString() {
        return "JobOffersResponse{" +
                "jobOffers=" + jobOffers +
                '}';
    }
    public static class JobOffer {
        @Json(name = "id")
        private String id;
        @Json(name = "intitule")
        private String title;



        @Json(name = "description")
        private String description;


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


    }
}
