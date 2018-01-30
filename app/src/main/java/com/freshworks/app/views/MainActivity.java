package com.freshworks.app.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freshworks.app.R;
import com.freshworks.app.adapters.GifFragmentAdapter;
import com.freshworks.app.data.Constant;
import com.freshworks.app.views.fragments.FavoriteGifFragment;
import com.freshworks.app.views.fragments.SearchGifFragment;

public class MainActivity extends AppCompatActivity implements SearchGifFragment.OnFavoriteSelectedListener{

    // UI Elements
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private GifFragmentAdapter mGifFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setChildrenViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!isNetworkAvailable()){
            showErrorDialog(Constant.ERROR_BODY_INTERNET_NOT_FOUND);
        }
    }

    private void setChildrenViews() {
        // Find the view pager that will allow the user to swipe between fragments
        mViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        // Create an adapter that knows which fragment should be shown on each page
        mGifFragmentAdapter = new GifFragmentAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        mViewPager.setAdapter(mGifFragmentAdapter);
        // Give the TabLayout the ViewPager
        mTabLayout = (TabLayout) findViewById(R.id.slideMainTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    //OnFavoriteSelected for interaction from one Fragment to Another. Fragments should never interact directly.
    @Override
    public void OnFavoriteSelected() {
        FavoriteGifFragment favoriteFragment = (FavoriteGifFragment) mGifFragmentAdapter.getRegisteredFragment(1);
        favoriteFragment.mFavoritePresenter.loadFavorites();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showErrorDialog(String errorMessage){
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(Constant.ERROR_TITLE)
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

