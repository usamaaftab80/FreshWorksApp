package com.freshworks.app.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.freshworks.app.R;
import com.freshworks.app.adapters.SearchGifRecyclerAdapter;
import com.freshworks.app.data.Constant;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;

import java.util.ArrayList;
import java.util.List;

public class SearchGifFragment extends Fragment {

    private static String TAG = "SearchGifFragment";

    private OnFragmentInteractionListener mListener;
    private RecyclerView mGifRecyclerView;
    private SearchGifRecyclerAdapter mSearchGifListAdapter;
    private GiphyListPresenter mGiphyListPresenter;

    private static GPHApi client = new GPHApiClient(Constant.GIPHY_API_KEY);
    public SearchGifFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mGiphyListPresenter = new GiphyListPresenter(getActivity(), this, client);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gif, container, false);
        ArrayList<Media> dummyGifs = new ArrayList<Media>();

        //At the start, give an empty list to adapter.
        mGifRecyclerView = (RecyclerView) rootView.findViewById(R.id.gif_recyclerview);
        mGifRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSearchGifListAdapter = new SearchGifRecyclerAdapter(getActivity(), dummyGifs);
        mGifRecyclerView.setAdapter(mSearchGifListAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Load it every time the fragment resumes, just to make sure that is loading the most recent trending.
        mGiphyListPresenter.loadTrending(Constant.LIST_OFFSET);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchable_menu, menu);

        // Implementing the search menu
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                mGiphyListPresenter.searchGifs(searchQuery, Constant.LIST_OFFSET);
                mGifRecyclerView.invalidate();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                if(!searchQuery.isEmpty()) {
                    mGiphyListPresenter.searchGifs(searchQuery, Constant.LIST_OFFSET);
                    mGifRecyclerView.invalidate();
                    return true;
                } else {
                  return false;
                }

            }
        });
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // reload the trending gifs
                mGiphyListPresenter.loadTrending(Constant.LIST_OFFSET);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        mSearchGifListAdapter.notifyData(gifs);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
