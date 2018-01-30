package com.freshworks.app.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.freshworks.app.R;
import com.freshworks.app.data.Constant;
import com.freshworks.app.views.MainActivity;

import com.giphy.sdk.core.models.Media;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class SearchGifRecyclerAdapter extends RecyclerView.Adapter<SearchGifRecyclerAdapter.LineHolder> {

    private static String TAG = "SearchGifRecyclerAdapter";

    private Context mContext;
    private static LayoutInflater inflater = null;
    private List<Media> mGifs;

    public SearchGifRecyclerAdapter(Context context, ArrayList<Media> gifs) {

        this.mContext = context;
        this.mGifs = gifs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gif, parent, false));
    }

    @Override
    public void onBindViewHolder(final LineHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder is called...");

        final Media gif = this.mGifs.get(position);

        if (gif.getTitle().isEmpty()) {
            holder.gifTextView.setText(R.string.title_not_found);
        } else {
            holder.gifTextView.setText(gif.getTitle());
        }

        Glide.with(mContext)
                .load(gif.getImages().getDownsized().getGifUrl())
                .into(holder.gifImageView);

        if (doesExistInSharedPreference(gif)) {
            toggleButton(holder.favoriteButton, true);
        }else {
            toggleButton(holder.favoriteButton, false);
        }
        holder.favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            MainActivity mainActivity = (MainActivity) mContext;

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isPressed()) {
                    if (isChecked) {
                        toggleButton(holder.favoriteButton, true);
                        Toast.makeText(mContext, Constant.MARKED_FAVORITE, Toast.LENGTH_LONG).show();
                        addToSharedPreferences(gif);
                        mainActivity.OnFavoriteSelected();
                    } else {
                        toggleButton(holder.favoriteButton, false);
                        Toast.makeText(mContext, Constant.MARKED_UNFAVORITE, Toast.LENGTH_LONG).show();
                        removeFromSharedPreferences(gif);
                        mainActivity.OnFavoriteSelected();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.mGifs.size();
    }

    public void notifyData(List<Media> gifList) {
        this.mGifs = gifList;
        notifyDataSetChanged();
    }

    public void toggleButton(ToggleButton button, boolean state) {
        if(state){
            button.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.favorite_filled));
        }else{
            button.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.favorite_grey));
        }
    }
    public void addToSharedPreferences(Media gif) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(Constant.SHARED_PREF_TITLE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String gifString = gson.toJson(gif);
        sharedPreferences.edit().putString(gif.getId(), gifString).commit();
        Log.d(TAG, "Id: " + gif.getId() + " added to Shared Preferences");
    }

    public void removeFromSharedPreferences(Media gif) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(Constant.SHARED_PREF_TITLE, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(gif.getId(), Constant.SHARED_PREF_KEY_NOT_FOUND).equals(Constant.SHARED_PREF_KEY_NOT_FOUND)) {
            Log.d(TAG, "The entry you are trying to delete does not exist in Shared Preferences");
        } else {
            sharedPreferences.edit().remove(gif.getId()).commit();
            Log.d(TAG, "Id: " + gif.getId() + " removed from Shared Preferences");
        }
    }

    public boolean doesExistInSharedPreference(Media gif) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(Constant.SHARED_PREF_TITLE, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(gif.getId(), Constant.SHARED_PREF_KEY_NOT_FOUND).equals(Constant.SHARED_PREF_KEY_NOT_FOUND)) {
            Log.d(TAG, "The entry you are trying to delete does not exist in Shared Preferences");
            return false;
        } else {
            Log.d(TAG, "Id: " + gif.getId() + " removed from Shared Preferences");
            return true;
        }
    }

    public class LineHolder extends RecyclerView.ViewHolder {

        TextView gifTextView;
        ImageView gifImageView;
        ToggleButton favoriteButton;

        public LineHolder(View itemView) {
            super(itemView);
            gifTextView = (TextView) itemView.findViewById(R.id.gif_textview);
            gifImageView = (ImageView) itemView.findViewById(R.id.gif_imageview);
            favoriteButton = (ToggleButton) itemView.findViewById(R.id.button_favorite);
            favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.favorite_grey));
        }
    }
}
