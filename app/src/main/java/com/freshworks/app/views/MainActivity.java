package com.freshworks.app.views;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freshworks.app.R;
import com.freshworks.app.adapters.GifFragmentAdapter;
import com.freshworks.app.data.Constant;
import com.freshworks.app.presenters.FavoritePresenter;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.freshworks.app.views.fragments.FavoriteGifFragment;
import com.freshworks.app.views.fragments.SearchGifFragment;

public class MainActivity extends AppCompatActivity implements SearchGifFragment.OnFragmentInteractionListener, FavoriteGifFragment.OnFragmentInteractionListener{

    // UI Elements
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setChildrenViews();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // implement custom fragent interaction implementation here...
    }

    private void setChildrenViews() {
        // Find the view pager that will allow the user to swipe between fragments
        mViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        // Create an adapter that knows which fragment should be shown on each page
        GifFragmentAdapter adapter = new GifFragmentAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Give the TabLayout the ViewPager
        mTabLayout = (TabLayout) findViewById(R.id.slideMainTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
