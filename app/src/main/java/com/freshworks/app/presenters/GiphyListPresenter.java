package com.freshworks.app.presenters;

import android.util.Log;

import com.freshworks.app.data.Constant;
import com.freshworks.app.views.fragments.SearchGifFragment;
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
    private SearchGifFragment mGiphySearchFragment;

    // Giphy Api Variables
    private GPHApi mGiphyApi;

    public GiphyListPresenter(SearchGifFragment searchFragment, GPHApi giphyApi) {
        this.mGiphySearchFragment = searchFragment;
        this.mGiphyApi = giphyApi;
    }

    //Load the trending GIFs where offset is the number of gifs to be loaded at a time.
    public void loadTrending(int offset) {
        mGiphyApi.trending(MediaType.gif, offset, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.d(TAG, Constant.NUMBER_OF_GIFS_DISPLAYING + result.getData().size());
                        for (Media gif : result.getData()) {
                            Log.d(TAG, gif.getId());
                        }
                        mGiphySearchFragment.displayGifs(result.getData());
                    } else {
                        Log.e(TAG, Constant.NO_RESULTS_FOUND);
                    }
                }
            }
        });
    }

    //Search the GIFs where offset is the number of gifs to be loaded at a time.
    public void searchGifs(String keyword, int offset) {
        mGiphyApi.search(keyword, MediaType.gif, offset, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.d(TAG, Constant.NUMBER_OF_GIFS_SEARCHED + result.getData().size());
                        for (Media gif : result.getData()) {
                            Log.d(TAG, gif.getId());
                        }
                        mGiphySearchFragment.displayGifs(result.getData());
                    } else {
                        Log.e(TAG, Constant.NO_RESULTS_FOUND);
                    }
                }
            }
        });
    }
}
