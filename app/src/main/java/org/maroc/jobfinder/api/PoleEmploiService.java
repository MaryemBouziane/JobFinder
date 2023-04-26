package org.maroc.jobfinder.api;



import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class PoleEmploiService {

    private static final String CLIENT_ID = "...";
    private static final String CLIENT_SECRET = "...";

    private final PoleEmploiApi api;

    public PoleEmploiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.emploi-store.fr")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        api = retrofit.create(PoleEmploiApi.class);
    }

    public String getAccessToken() throws IOException {
        String response = api.getAccessToken(
                "client_credentials",
                CLIENT_ID,
                CLIENT_SECRET,
                "api_offresdemploiv2 o2dsoffre"
        ).execute().body();

        return response;
    }

    public String searchJobOffers(String accessToken, String motsCles, String localisation, String range) throws IOException {
        String response = api.searchJobOffers(motsCles, localisation, range)
                .execute()
                .body();

        return response;
    }
}
