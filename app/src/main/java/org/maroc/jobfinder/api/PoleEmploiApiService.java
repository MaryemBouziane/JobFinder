package org.maroc.jobfinder.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoleEmploiApiService {
    @FormUrlEncoded
    @POST("https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=%2Fpartenaire")
    Call<AccessToken> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope
    );

    @GET("https://api.emploi-store.fr/partenaire/offresdemploi/v2/offres/search")
    Call<JobOffersResponse> searchJobOffers(
            @Header("Authorization") String authorization,
            @Query("motsCles") String keywords,
            @Query("localisation") String location,
            @Query("range") String range
    );
}
