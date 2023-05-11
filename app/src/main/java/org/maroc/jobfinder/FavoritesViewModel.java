package org.maroc.jobfinder;



import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maroc.jobfinder.database.JobFinderRepository;
import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private JobFinderRepository repository;
    private LiveData<List<JobOffer>> allFavoriteJobOffers;

    public FavoritesViewModel(Application application) {
        super(application);
        repository = new JobFinderRepository(application);
        allFavoriteJobOffers = repository.getAllJobOffers();
    }

    public LiveData<List<JobOffer>> getAllFavoriteJobOffers() {
        return allFavoriteJobOffers;
    }

    public void insertJobOffer(JobOffer jobOffer) {
        repository.insertJobOffer(jobOffer);
    }

    public void deleteJobOffer(String id) {
        repository.deleteJobOffer(id);
    }
    public LiveData<List<JobOffer>> findJobOffersByContractType(String typeContrat) {
        return repository.findJobOffersByContractType(typeContrat);
    }

    public LiveData<List<JobOffer>> findJobOffersWithMinPostes(int minPostes) {
        return repository.findJobOffersWithMinPostes(minPostes);
    }

    public LiveData<List<JobOffer>> findJobOffersLackingCandidates() {
        return repository.findJobOffersLackingCandidates();
    }
    public void findJobOffer(String id) {
        repository.findJobOffer(id);
    }
}