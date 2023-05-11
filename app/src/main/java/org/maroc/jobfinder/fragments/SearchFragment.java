package org.maroc.jobfinder.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import org.maroc.jobfinder.JobOfferAdapter;
import org.maroc.jobfinder.JobOfferDetailsActivity;
import org.maroc.jobfinder.R;
import org.maroc.jobfinder.api.AccessToken;
import org.maroc.jobfinder.api.JobOffersResponse;
import org.maroc.jobfinder.api.PoleEmploiApi;
import org.maroc.jobfinder.api.QueryMapBuilder;
import org.maroc.jobfinder.models.JobOffer;
import org.maroc.jobfinder.models.SelectedJobOffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements JobOfferAdapter.OnJobOfferClickListener {

    private TextInputEditText searchInput;
    private RecyclerView recyclerView;
    private JobOfferAdapter jobOfferAdapter;
    private ImageButton searchButton;

    // Ajoutez ces membres à votre classe
    private List<JobOffer> JobOffers;
    private JobOffersResponse jobOffersApiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchInput = view.findViewById(R.id.searchEditText);
        recyclerView = view.findViewById(R.id.resultsRecyclerView);

        JobOffers = new ArrayList<>();
        jobOfferAdapter = new JobOfferAdapter(JobOffers, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(jobOfferAdapter);
         searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            Log.d("BUTTON_CLICK", "Search button clicked");
            Toast.makeText(getContext(), "Search button clicked", Toast.LENGTH_SHORT).show();
            performSearch(searchInput.getText().toString());
        });


    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Veuillez entrer un terme de recherche.", Toast.LENGTH_SHORT).show();
            return;
        }
        PoleEmploiApi api = new PoleEmploiApi();
        // Obtenir l'access token
        api.getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    String accessToken = response.body().getAccessToken();

                    // Rechercher des offres d'emploi avec l'access token

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("recherche", getContext().MODE_PRIVATE);
                    Map<String, String> filters = QueryMapBuilder.createQueryMap(sharedPreferences);
                    api.searchJobOffers(accessToken, query, filters).enqueue(new Callback<JobOffersResponse>() {
                        @Override
                        public void onResponse(Call<JobOffersResponse> call, Response<JobOffersResponse> response) {
                            if (response.isSuccessful()) {
                                JobOffersResponse jobOffers = response.body();
                              //  System.out.println("1");

                                //System.out.println(jobOffers);
                           //     System.out.println( jobOffers.getJobOffers().get(0));
                                if(jobOffers!=null) {
                                    Toast.makeText(getContext(), jobOffers.getJobOffers().get(0).getId(), Toast.LENGTH_SHORT).show();
                                    Log.d("BUTTON_CLICK", jobOffers.getJobOffers().get(0).getTitle());
                                    jobOfferAdapter.setJobOffers(jobOffers.getJobOffers());
                                    jobOfferAdapter.notifyDataSetChanged();

                                }else{
                                    Toast.makeText(getContext(),  "not found", Toast.LENGTH_SHORT).show();
                                } //  System.out.println("2");
                            } else {
                              //  System.out.println("Erreur lors de la récupération des offres d'emploi");
                                Toast.makeText(getContext(),  "Erreur lors de la récupération des offres d'emploi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JobOffersResponse> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                } else {
                   // System.out.println("Erreur lors de la récupération de l'access token");
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onJobOfferClick(JobOffer jobOffer) {
        Intent intent = new Intent(getContext(), JobOfferDetailsActivity.class);
        intent.putExtra(JobOfferDetailsActivity.EXTRA_JOB_OFFER, jobOffer);
        startActivity(intent);
    }


}
