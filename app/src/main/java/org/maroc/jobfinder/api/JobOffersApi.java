package org.maroc.jobfinder.api;

import com.google.gson.JsonObject;

import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import retrofit2.Call;
import retrofit2.http.*;

public interface JobOffersApi {

    @FormUrlEncoded
    @POST("connexion/oauth2/access_token?realm=%2Fpartenaire")
    Call<AccessToken> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope);

    @GET("partenaire/offresdemploi/v2/offres/search")
    Call<JsonObject> searchJobOffers(
            @Header("Authorization") String authorization,
            @Query("motsCles") String keywords,
            @Query("localisation") String location,
            @Query("range") String range);
}
