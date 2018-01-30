package com.freshworks.app.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freshworks.app.R;
import com.freshworks.app.data.Constant;
import com.freshworks.app.presenters.FavoritePresenter;

public class FavoriteGifFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FavoritePresenter mFavoritePresenter;

    public FavoriteGifFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoritePresenter = new FavoritePresenter(getActivity().getSharedPreferences(Constant.SHARED_PREF_TITLE, Context.MODE_PRIVATE), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFavoritePresenter.loadFavorites();
        return inflater.inflate(R.layout.fragment_favorite_gif, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
