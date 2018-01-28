package com.freshworks.app.services;

import android.util.Log;

import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import java.util.List;

/**
 * Created by Usama Aftab on 2018-01-25.
 */

public class GiphyService {
    private static String TAG = "GiphyService";

    private static GPHApi client = new GPHApiClient("HCJPOrE9Ytk3MxU60FZ3wIegQk2tH42u");

    private static GiphyService mGiphyService = null;

    public static GiphyService getInstance(){
        if(mGiphyService == null) {
            mGiphyService = new GiphyService();
        }
        return mGiphyService;
    }

    public void loadTrending(int offset) {
        client.trending(MediaType.gif, offset, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.d(TAG, "The number of gifs displaying are: " +result.getData().size());
                        for (Media gif : result.getData()) {
                            Log.d(TAG, gif.getId());
                        }
                    } else {
                        Log.e(TAG, "No results found");
                    }
                }
            }
        });
    }
    public void searchGifs(String keyword, int offset) {
        client.search(keyword, MediaType.gif, offset, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.d(TAG, "The number of gifs searched are: " + result.getData().size());
                        for (Media gif : result.getData()) {
                            Log.d(TAG, gif.getId());
                        }
                    } else {
                        Log.e(TAG, "No results found");
                    }
                }
            }
        });
    }
}
