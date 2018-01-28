package com.freshworks.app.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.freshworks.app.R;
import com.freshworks.app.adapters.SearchGifListAdapter;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;

import java.util.ArrayList;
import java.util.List;

public class SearchGifFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ListView mGifListView;
    private SearchGifListAdapter mSearchGifListAdapter;
    private GiphyListPresenter mGiphyListPresenter;

    private static GPHApi client = new GPHApiClient("HCJPOrE9Ytk3MxU60FZ3wIegQk2tH42u");

    public SearchGifFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGiphyListPresenter = new GiphyListPresenter(getActivity(), this, client);
        mGiphyListPresenter.loadTrending(10);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gif, container, false);
        ArrayList<Media> dummyGifs = new ArrayList<Media>();

        mGifListView = (ListView) rootView.findViewById(R.id.gif_listview);
        mSearchGifListAdapter = new SearchGifListAdapter(getActivity(), dummyGifs);
        mGifListView.setAdapter(mSearchGifListAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void displayGifs(List<Media> gifs) {

        mSearchGifListAdapter.clear();
        mSearchGifListAdapter.addAll(gifs);
        mSearchGifListAdapter.notifyDataSetInvalidated();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
