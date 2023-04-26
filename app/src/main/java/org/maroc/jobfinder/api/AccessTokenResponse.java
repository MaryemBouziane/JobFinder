package org.maroc.jobfinder.api;

import com.google.gson.annotations.SerializedName;

public class AccessTokenResponse {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
