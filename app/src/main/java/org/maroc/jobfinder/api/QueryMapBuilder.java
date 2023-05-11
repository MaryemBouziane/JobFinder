package org.maroc.jobfinder.api;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class QueryMapBuilder {

    public static Map<String, String> createQueryMap(SharedPreferences sharedPreferences) {
        Map<String, String> queryMap = new HashMap<>();
        if (isSharedPreferencesStringValid(sharedPreferences, "maxResults"))
            queryMap.put("range", "0-"+sharedPreferences.getString("maxResults", null));
        if (isSharedPreferencesStringValid(sharedPreferences, "secteurActivite"))
            queryMap.put("secteurActivite", sharedPreferences.getString("secteurActivite", null));
        if (isSharedPreferencesStringValid(sharedPreferences, "typeContrat"))
            queryMap.put("typeContrat", sharedPreferences.getString("typeContrat", null));
        if (isSharedPreferencesStringValid(sharedPreferences, "commune"))
            queryMap.put("commune", sharedPreferences.getString("commune", null));
        if (isSharedPreferencesStringValid(sharedPreferences, "distance"))
            queryMap.put("distance", sharedPreferences.getString("distance", null));
        if (isSharedPreferencesStringValid(sharedPreferences, "niveauFormation"))
            queryMap.put("niveauFormation", sharedPreferences.getString("niveauFormation", null));

        return queryMap;
    }

    private static boolean isSharedPreferencesStringValid(SharedPreferences sharedPreferences, String key) {
        if (sharedPreferences.getString(key, null) != null && sharedPreferences.getString(key, null) != "")
            return true;
        return false;
    }
}
