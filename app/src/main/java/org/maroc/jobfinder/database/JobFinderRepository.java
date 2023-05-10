package org.maroc.jobfinder.database;

import static org.maroc.jobfinder.database.JobFinderDatabase.databaseWriteExecutor;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maroc.jobfinder.models.JobOffer;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class JobFinderRepository {
    private MutableLiveData<List<JobOffer>> searchResults = new MutableLiveData<>();
    private LiveData<List<JobOffer>> allJobOffers;

    private JobOfferDao jobOfferDao;

    public JobFinderRepository(Application application) {
        JobFinderDatabase db = JobFinderDatabase.getDatabase(application);
        jobOfferDao = db.jobOfferDao();
        allJobOffers = jobOfferDao.getAllJobOffers();
    }

    public void insertJobOffer(JobOffer jobOffer) {
        databaseWriteExecutor.execute(() -> {
            jobOfferDao.insertJobOffer(jobOffer);
        });
    }

    public void deleteJobOffer(String id) {
        databaseWriteExecutor.execute(() -> {
            jobOfferDao.deleteJobOffer(id);
        });
    }

    public void findJobOffer(String id) {
        Future<List<JobOffer>> jobOffersFuture = databaseWriteExecutor.submit(() -> {
            return jobOfferDao.findJobOffer(id);
        });
        try {
            searchResults.setValue(jobOffersFuture.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<JobOffer>> getAllJobOffers() {
        return allJobOffers;
    }

    public MutableLiveData<List<JobOffer>> getSearchResults() {
        return searchResults;
    }
}
