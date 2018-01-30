package com.freshworks.app.views.fragments;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freshworks.app.R;
import com.freshworks.app.adapters.FavoriteGifRecyclerAdapter;
import com.freshworks.app.data.Constant;
import com.freshworks.app.presenters.FavoritePresenter;
import com.giphy.sdk.core.models.Media;

import java.util.ArrayList;

public class FavoriteGifFragment extends Fragment {

    public FavoritePresenter mFavoritePresenter;
    private FavoriteGifRecyclerAdapter mFavoriteGifRecyclerAdapter;
    private RecyclerView favoriteRecyclerView;
    private TextView mFavoritesNotFoundTextView;

    public FavoriteGifFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoritePresenter = new FavoritePresenter(getActivity().getSharedPreferences(Constant.SHARED_PREF_TITLE, Context.MODE_PRIVATE), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite_gif, container, false);

        // set up the RecyclerView
        favoriteRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylerview_favorites);
        mFavoritesNotFoundTextView = (TextView) rootView.findViewById(R.id.textview_no_favorite);
        int numberOfColumns = 2;
        ArrayList<Media> dummyFavoriteGifs = new ArrayList<>();

        favoriteRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        mFavoriteGifRecyclerAdapter = new FavoriteGifRecyclerAdapter(getActivity(), dummyFavoriteGifs);
        favoriteRecyclerView.setAdapter(mFavoriteGifRecyclerAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFavoritePresenter.loadFavorites();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void displayFavorites(ArrayList<Media> favoriteGifs) {
        if(favoriteGifs.size() == 0){
            mFavoritesNotFoundTextView.setVisibility(View.VISIBLE);
        }else{
            mFavoritesNotFoundTextView.setVisibility(View.INVISIBLE);
        }
        mFavoriteGifRecyclerAdapter.notifyData(favoriteGifs);
    }
}
