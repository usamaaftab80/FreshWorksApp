package com.freshworks.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.freshworks.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usama Aftab on 2018-01-27.
 */

public class SearchGifListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mGifUrls;
    private ArrayList<String> mGifNames;
    private static LayoutInflater inflater = null;

    public SearchGifListAdapter(Context context, List<String> gifNames, List<String> gifUrls) {
        this.mContext = context;
        this.mGifUrls = (ArrayList) gifUrls;
        this.mGifNames = (ArrayList) gifNames;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mGifUrls.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.item_gif, null);
        holder.gifTextView = (TextView) rowView.findViewById(R.id.gif_textview);
        holder.gifImageView = (ImageView) rowView.findViewById(R.id.gif_imageview);

        holder.gifTextView.setText(mGifNames.get(position));
        String url = mGifUrls.get(position);

        Glide.with(mContext)
             .load(url)
             .into(holder.gifImageView);
        return rowView;
    }

    public class Holder
    {
        TextView gifTextView;
        ImageView gifImageView;
    }
}
