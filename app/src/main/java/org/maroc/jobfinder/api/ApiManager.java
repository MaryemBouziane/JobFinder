package org.maroc.jobfinder.api;
//classe pour gérer les requetes API
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "https://api.emploi-store.fr/";
    private static final String CLIENT_ID = "PAR_applicationpourtrouve_69c49c8f63613d29cc8f694bcdd24c5df2681f438f8fadeb269c682abd49990e";
    private static final String CLIENT_SECRET = "dc837ff93feda7a6e0cbad73995ac8f8194e9e2fd36896c9997e0ec14f5116b0";
   private static JobOffersApi jobOffersApi;

    public static JobOffersApi getJobOffersApi() {
        if (jobOffersApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            jobOffersApi = retrofit.create(JobOffersApi.class);
        }
        return jobOffersApi;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static String getClientSecret() {
        return CLIENT_SECRET;
    }
}

