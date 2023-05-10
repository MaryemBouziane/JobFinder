package org.maroc.jobfinder.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maroc.jobfinder.FavoritesViewModel;
import org.maroc.jobfinder.JobOfferListAdapter;
import org.maroc.jobfinder.R;
import org.maroc.jobfinder.models.JobOffer;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    private JobOfferListAdapter adapter;
    private FavoritesViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        observerSetup();
        recyclerSetup();
    }

    private void observerSetup() {

        Log.d("FavoritesFragment", "observerSetup called");
        viewModel.getAllFavoriteJobOffers().observe(getViewLifecycleOwner(),
                new Observer<List<JobOffer>>() {
                    @Override
                    public void onChanged(@Nullable final List<JobOffer> jobOffers) {
                        adapter.setJobOfferList(jobOffers);

                        Log.d("FavoritesFragment", "Data changed, updating adapter");
                        Log.d("FavoritesFragment", "Number of job offers: " + jobOffers.size());
                      }
                });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new JobOfferListAdapter(R.layout.job_offer_list_item);
        recyclerView = getView().findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }
}