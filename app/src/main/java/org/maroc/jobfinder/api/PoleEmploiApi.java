package org.maroc.jobfinder.api;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PoleEmploiApi {
    private static final String CLIENT_ID = "PAR_applicationpourtrouve_69c49c8f63613d29cc8f694bcdd24c5df2681f438f8fadeb269c682abd49990e";
    private static final String CLIENT_SECRET = "dc837ff93feda7a6e0cbad73995ac8f8194e9e2fd36896c9997e0ec14f5116b0";
    private static final String SCOPE = "api_offresdemploiv2 o2dsoffre";
    private static final String GRANT_TYPE = "client_credentials";

    private final PoleEmploiApiService service;

    public PoleEmploiApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.emploi-store.fr")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        service = retrofit.create(PoleEmploiApiService.class);
    }

    public Call<AccessToken> getAccessToken() {
        return service.getAccessToken(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET, SCOPE);
    }

    public Call<JobOffersResponse> searchJobOffers(String accessToken, String keywords, Map<String, String> filters) {
        String authorization = "Bearer " + accessToken;
        return service.searchJobOffers(authorization, keywords, filters);
    }
}
