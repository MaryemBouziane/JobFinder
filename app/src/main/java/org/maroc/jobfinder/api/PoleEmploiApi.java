package org.maroc.jobfinder.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PoleEmploiApi {

    @FormUrlEncoded
    @POST("https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=%2Fpartenaire")
    Call<String> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope
    );

    @POST("https://api.emploi-store.fr/partenaire/offresdemploi/v2/offres/search")
    Call<String> searchJobOffers(
            @Query("motsCles") String motsCles,
            @Query("localisation") String localisation,
            @Query("range") String range
    );
}
