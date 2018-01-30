package com.freshworks.app.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.freshworks.app.views.fragments.FavoriteGifFragment;
import com.giphy.sdk.core.models.Media;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Usama Aftab on 2018-01-28.
 */

public class FavoritePresenter {
    private static String TAG = "FavoritePresenter";

    private FavoriteGifFragment mGiphyFavoriteFragment;
    private SharedPreferences mSharedPreferences;

    public FavoritePresenter(SharedPreferences sharedPreferences, FavoriteGifFragment favoriteFragment) {
        this.mGiphyFavoriteFragment = favoriteFragment;
        this.mSharedPreferences = sharedPreferences;
    }

    public void loadFavorites(){
        Map<String, ?> favoritesMap = (Map<String, String>) this.mSharedPreferences.getAll();
        ArrayList<Media> favoriteGifs = new ArrayList<>();

        Gson gson = new Gson();

        for(Map.Entry<String,?> entry : favoritesMap.entrySet()){
            Media gif = gson.fromJson(entry.getValue().toString(), Media.class);
            favoriteGifs.add(gif);
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
        }
        this.mGiphyFavoriteFragment.displayFavorites(favoriteGifs);
    }
}
