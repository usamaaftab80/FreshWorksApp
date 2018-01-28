package com.freshworks.app.presenters;

import android.widget.ListView;

import com.freshworks.app.services.GiphyService;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class GiphyListPresenter {

    private GiphyService mGiphyService;
    private ListView mGiphyListView;

    public GiphyListPresenter(ListView listView, GiphyService giphyService) {
        this.mGiphyListView = listView;
        this.mGiphyService = giphyService;
    }

    public void loadGifsByTrend(int offset) {
        this.mGiphyService.loadTrending(offset);
    }

    public void loadGifsByKeyword(String keyword, int offset) {
        this.mGiphyService.searchGifs(keyword, offset);
    }
}
