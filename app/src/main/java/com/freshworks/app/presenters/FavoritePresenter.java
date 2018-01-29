package com.freshworks.app.presenters;

import android.content.SharedPreferences;

import com.freshworks.app.views.fragments.SearchGifFragment;
import com.giphy.sdk.core.models.Media;

/**
 * Created by Usama Aftab on 2018-01-28.
 */

public class FavoritePresenter {
    private static String TAG = "FavoritePresenter";
    private SearchGifFragment mGiphySearchFragment;
    private SharedPreferences mSharedPreferences;

    public FavoritePresenter(SharedPreferences sharedPreferences, SearchGifFragment searchFragment) {
        this.mGiphySearchFragment = searchFragment;
        this.mSharedPreferences = sharedPreferences;
    }
}
