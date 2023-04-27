package org.maroc.jobfinder.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import org.maroc.jobfinder.JobOfferAdapter;
import org.maroc.jobfinder.R;
import org.maroc.jobfinder.api.PoleEmploiService;
import org.maroc.jobfinder.models.JobOffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private TextInputEditText searchInput;
    private RecyclerView recyclerView;
    private JobOfferAdapter jobOfferAdapter;
    private ImageButton searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchInput = view.findViewById(R.id.searchEditText);
        recyclerView = view.findViewById(R.id.resultsRecyclerView);

        jobOfferAdapter = new JobOfferAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(jobOfferAdapter);

        ImageButton searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            Log.d("BUTTON_CLICK", "Search button clicked");
            Toast.makeText(getContext(), "Search button clicked", Toast.LENGTH_SHORT).show();
            performSearch(searchInput.getText().toString());
        });


    }
    private void performSearch(String query) {
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Veuillez entrer un terme de recherche", Toast.LENGTH_SHORT).show();
            return;
        }

        PoleEmploiService poleEmploiService = new PoleEmploiService();
        Executor executor = Executors.newSingleThreadExecutor();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    String accessToken = poleEmploiService.getAccessToken();
                    Log.d("PERFORM_SEARCH", "Access token: " + accessToken);

                    String searchResult = poleEmploiService.searchJobOffers(accessToken, query, "75", "0-9");
                    Log.d("PERFORM_SEARCH", "Search result: " + searchResult);

                    // Convert the searchResult JSON to a list of JobOffer objects
                    List<JobOffer> jobOffers = JobOffer.fromJsonArray(searchResult);
                    Log.d("PERFORM_SEARCH", "Job offers size: " + jobOffers.size());

                    getActivity().runOnUiThread(() -> {
                        jobOfferAdapter.updateData(jobOffers);
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("PERFORM_SEARCH", "Error: " + e.getMessage());
                }
                return null;
            }
        }.executeOnExecutor(executor);
    }


}
