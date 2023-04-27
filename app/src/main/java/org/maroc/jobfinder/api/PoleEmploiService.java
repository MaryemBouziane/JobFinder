package org.maroc.jobfinder.api;



import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class PoleEmploiService {

    private static final String CLIENT_ID = "PAR_applicationpourtrouve_69c49c8f63613d29cc8f694bcdd24c5df2681f438f8fadeb269c682abd49990e";
    private static final String CLIENT_SECRET = "dc837ff93feda7a6e0cbad73995ac8f8194e9e2fd36896c9997e0ec14f5116b0";

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
