package com.freshworks.app.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freshworks.app.R;
import com.freshworks.app.views.fragments.FavoriteGifFragment;
import com.freshworks.app.views.fragments.SearchGifFragment;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class GifFragmentAdapter extends FragmentPagerAdapter {

    private static String TAG = "GifFragmentAdapter";
    private static int mTotalFragmentCount = 2;

    private Context mContext;

    public GifFragmentAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SearchGifFragment();
        } else if (position == 1) {
            return new FavoriteGifFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTotalFragmentCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.gif_trending_search_fragment);
            case 1:
                return mContext.getString(R.string.gif_favorite_fragment);
            default:
                return null;
        }
    }
}
