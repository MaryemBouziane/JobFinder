package org.maroc.jobfinder.models;



import org.maroc.jobfinder.models.JobOffer;

public class SelectedJobOffer {
    private static SelectedJobOffer instance;
    private JobOffer selectedJobOffer;

    private SelectedJobOffer() {
    }

    public static SelectedJobOffer getInstance() {
        if (instance == null) {
            instance = new SelectedJobOffer();
        }
        return instance;
    }

    public JobOffer getSelectedJobOffer() {
        return selectedJobOffer;
    }

    public void setSelectedJobOffer(JobOffer selectedJobOffer) {
        this.selectedJobOffer = selectedJobOffer;
    }
}
