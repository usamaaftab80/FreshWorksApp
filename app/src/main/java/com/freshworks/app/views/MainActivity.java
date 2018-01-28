package com.freshworks.app.views;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.freshworks.app.R;
import com.freshworks.app.adapters.GifFragmentAdapter;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.freshworks.app.views.fragments.FavoriteGifFragment;
import com.freshworks.app.views.fragments.SearchGifFragment;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;

public class MainActivity extends AppCompatActivity implements SearchGifFragment.OnFragmentInteractionListener, FavoriteGifFragment.OnFragmentInteractionListener{

    private GiphyListPresenter mGiphyListPresenter;
    private static GPHApi client = new GPHApiClient("HCJPOrE9Ytk3MxU60FZ3wIegQk2tH42u");

    // UI Elements
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mGiphyListPresenter = new GiphyListPresenter(new ListView(this), client);
        //mGiphyListPresenter.loadTrending(10);

        setChildrenViews();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void setChildrenViews() {
        // Find the view pager that will allow the user to swipe between fragments
        mViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        // Create an adapter that knows which fragment should be shown on each page
        GifFragmentAdapter adapter = new GifFragmentAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        mViewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        mTabLayout = (TabLayout) findViewById(R.id.slideMainTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
