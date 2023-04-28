package org.maroc.jobfinder.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PoleEmploiApi api = new PoleEmploiApi();

        // Obtenir l'access token
        api.getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    String accessToken = response.body().getAccessToken();

                    // Rechercher des offres d'emploi avec l'access token
                    api.searchJobOffers(accessToken, "developpeur", "75", "0-9").enqueue(new Callback<JobOffersResponse>() {
                        @Override
                        public void onResponse(Call<JobOffersResponse> call, Response<JobOffersResponse> response) {
                            if (response.isSuccessful()) {
                                JobOffersResponse jobOffers = response.body();

                                System.out.println( jobOffers.getJobOffers().get(0).getTitle());
                                System.out.println("2");
                            } else {
                                System.out.println("Erreur lors de la récupération des offres d'emploi");
                            }
                        }

                        @Override
                        public void onFailure(Call<JobOffersResponse> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                } else {
                    System.out.println("Erreur lors de la récupération de l'access token");
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
