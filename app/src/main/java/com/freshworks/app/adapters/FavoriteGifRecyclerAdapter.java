package com.freshworks.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.giphy.sdk.core.models.Media;

import com.freshworks.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usama Aftab on 2018-01-29.
 */

public class FavoriteGifRecyclerAdapter extends RecyclerView.Adapter<FavoriteGifRecyclerAdapter.CellHolder>{

    private List<Media> mFavoriteGifs;
    private LayoutInflater mInflater;
    private Context mContext;

    public FavoriteGifRecyclerAdapter(Context context, ArrayList<Media> favoriteGifs) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mFavoriteGifs = favoriteGifs;
    }

    @Override
    public CellHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_item_favorite, parent, false);
        return new CellHolder(view);
    }

    @Override
    public void onBindViewHolder(CellHolder holder, int position) {
        Media favoriteGif = mFavoriteGifs.get(position);

        //Using Glide to load gif in the imageview.
        Glide.with(mContext)
                .load(favoriteGif.getImages().getDownsized().getGifUrl())
                .into(holder.gifImageView);
    }

    @Override
    public int getItemCount() {
        return this.mFavoriteGifs.size();
    }

    public void notifyData(List<Media> gifList) {
        this.mFavoriteGifs = gifList;
        notifyDataSetChanged();
    }

    public class CellHolder extends RecyclerView.ViewHolder {

        ImageView gifImageView;

        public CellHolder(View itemView) {
            super(itemView);
            gifImageView = (ImageView) itemView.findViewById(R.id.imageview_gif_favorite);
        }
    }
}
