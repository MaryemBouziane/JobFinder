package org.maroc.jobfinder.api;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import org.maroc.jobfinder.models.JobOffer;

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

}
