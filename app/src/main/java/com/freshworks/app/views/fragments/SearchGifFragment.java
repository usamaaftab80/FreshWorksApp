package com.freshworks.app.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.freshworks.app.R;
import com.freshworks.app.adapters.SearchGifListAdapter;
import com.freshworks.app.presenters.GiphyListPresenter;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;

import java.util.ArrayList;
import java.util.List;

public class SearchGifFragment extends Fragment {

    private static String TAG = "SearchGifFragment";

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
        setHasOptionsMenu(true);
        mGiphyListPresenter = new GiphyListPresenter(getActivity(), this, client);
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
    public void onResume() {
        super.onResume();

        mGiphyListPresenter.loadTrending(10);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchable_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                mGiphyListPresenter.searchGifs(searchQuery, 10);
                mGifListView.invalidate();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                if(!searchQuery.isEmpty()) {
                    mGiphyListPresenter.searchGifs(searchQuery, 10);
                    mGifListView.invalidate();
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
                mGiphyListPresenter.loadTrending(10);
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

        mSearchGifListAdapter.clear();
        mSearchGifListAdapter.addAll(gifs);
        mSearchGifListAdapter.notifyDataSetInvalidated();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
