package com.freshworks.app.adapters;

import android.content.Context;
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

public class SearchGifListAdapter extends ArrayAdapter<Media> {

    private Context mContext;
    private static LayoutInflater inflater = null;
    private List<Media> mGifs;

    public SearchGifListAdapter(Context context, ArrayList<Media> gifs) {
        super(context, 0, gifs);

        this.mContext = context;
        this.mGifs = gifs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        Media gif = getItem(position);
        Holder holder = new Holder();

        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_gif, viewGroup, false);

        holder.gifTextView = (TextView) view.findViewById(R.id.gif_textview);
        holder.gifImageView = (ImageView) view.findViewById(R.id.gif_imageview);

        if (gif.getTitle().isEmpty()) {
            holder.gifTextView.setText(R.string.title_not_found);
        } else {
            holder.gifTextView.setText(gif.getTitle());
        }

        Glide.with(mContext)
                .load(gif.getImages().getDownsized().getGifUrl())
                .into(holder.gifImageView);

        return view;
    }
    public class Holder {
        TextView gifTextView;
        ImageView gifImageView;
    }
}
