package org.maroc.jobfinder.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.maroc.jobfinder.models.JobOffer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.maroc.jobfinder.models.JobOffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.maroc.jobfinder.models.JobOffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {JobOffer.class}, version = 1)
public abstract class JobFinderDatabase extends RoomDatabase {

    public abstract JobOfferDao jobOfferDao();

    private static volatile JobFinderDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static JobFinderDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (JobFinderDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    JobFinderDatabase.class, "job_offers_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}