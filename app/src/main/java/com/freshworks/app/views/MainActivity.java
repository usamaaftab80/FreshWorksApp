package com.freshworks.app.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.freshworks.app.R;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.freshworks.app.services.GiphyService;

public class MainActivity extends AppCompatActivity {

    private GiphyListPresenter mGiphyListPresenter;
    private GiphyService mGiphyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGiphyService = GiphyService.getInstance();
        mGiphyListPresenter = new GiphyListPresenter(new ListView(this), mGiphyService);
        mGiphyListPresenter.loadGifsByTrend(10);
    }
}
