package org.maroc.jobfinder.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

@Dao
public interface JobOfferDao {
    @Insert
    void insertJobOffer(JobOffer jobOffer);

    @Query("SELECT * FROM JobOffer WHERE id = :id")
    List<JobOffer> findJobOffer(String id);

    @Query("DELETE FROM JobOffer WHERE id = :id")
    void deleteJobOffer(String id);

    @Query("SELECT * FROM JobOffer")
    LiveData<List<JobOffer>> getAllJobOffers();
}
