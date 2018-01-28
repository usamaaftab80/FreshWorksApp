package com.freshworks.app.presenters;

import android.util.Log;
import android.widget.ListView;

import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.response.ListMediaResponse;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class GiphyListPresenter {

    // Presenter variables
    private static String TAG = "GiphyListPresenter";
    private ListView mGiphyListView;

    // Giphy Api Variables
    private GPHApi mGiphyApi;

    public GiphyListPresenter(ListView listView, GPHApi giphyApi) {
        this.mGiphyListView = listView;
        this.mGiphyApi = giphyApi;
    }

    public void loadTrending(int offset) {
        mGiphyApi.trending(MediaType.gif, offset, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.d(TAG, "The number of gifs displaying are: " + result.getData().size());
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
        mGiphyApi.search(keyword, MediaType.gif, offset, null, null, null, new CompletionHandler<ListMediaResponse>() {
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
