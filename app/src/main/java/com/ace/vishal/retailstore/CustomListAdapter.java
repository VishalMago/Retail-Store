package com.ace.vishal.retailstore;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;

/**
 * Created by Hp on 9/13/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Vegetables> movieItems;
    public View.OnClickListener listener;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    static class ViewHolder {
        ImageButton cart;
    }

    public CustomListAdapter(Activity activity, List<Vegetables> movieItems,View.OnClickListener listener) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.listener=listener;
    }
    public void setButtonListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        ViewHolder holder = new ViewHolder();
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        holder.cart=(ImageButton) convertView.findViewById(R.id.cart);
        convertView.setTag(holder);
        if (this.listener != null) {
            holder.cart.setOnClickListener(this.listener);
        }
        // getting movie data for the row
        Vegetables m = movieItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        rating.setText( String.valueOf(m.getRating()));
        if(rating.getText().toString().equalsIgnoreCase("Available")){
            rating.setTextColor(Color.GREEN);
        }
        else{
            rating.setTextColor(Color.RED);
        }
        // genre
        // release year
        year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}
