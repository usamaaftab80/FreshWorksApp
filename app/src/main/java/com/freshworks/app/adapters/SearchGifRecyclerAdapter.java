package com.freshworks.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freshworks.app.R;
import com.giphy.sdk.core.models.Media;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class SearchGifRecyclerAdapter extends RecyclerView.Adapter<SearchGifRecyclerAdapter.LineHolder> {

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
    public void onBindViewHolder(LineHolder holder, int position) {
        Media gif = this.mGifs.get(position);

        if (gif.getTitle().isEmpty()) {
            holder.gifTextView.setText(R.string.title_not_found);
        } else {
            holder.gifTextView.setText(gif.getTitle());
        }

        Glide.with(mContext)
                .load(gif.getImages().getDownsized().getGifUrl())
                .into(holder.gifImageView);

    }

    @Override
    public int getItemCount() {
        return this.mGifs.size();
    }

    public void notifyData(List<Media> gifList) {
        this.mGifs = gifList;
        notifyDataSetChanged();
    }

    public class LineHolder extends RecyclerView.ViewHolder {

        TextView gifTextView;
        ImageView gifImageView;

        public LineHolder(View itemView) {
            super(itemView);
            gifTextView = (TextView) itemView.findViewById(R.id.gif_textview);
            gifImageView = (ImageView) itemView.findViewById(R.id.gif_imageview);
        }
    }
}