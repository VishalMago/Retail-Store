package com.ace.vishal.retailstore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hp on 1/10/2017.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private List<cart_contents> driverList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,quantity,availability;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewTitle);
            availability = (TextView) view.findViewById(R.id.textViewAvailability);
            image = (ImageView) view.findViewById(R.id.imageView);
            price=(TextView)view.findViewById(R.id.textViewprice);
            quantity=(TextView)view.findViewById(R.id.textViewQuantity);
        }
    }


    public CartAdapter(List<cart_contents> driverList) {
        this.driverList = driverList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        cart_contents driver = driverList.get(position);
        holder.title.setText(driver.getTitle());
        holder.quantity.setText(driver.getQuantity());
        holder.image.setImageResource(driver.getImage());
        holder.price.setText(driver.getPrice());
        holder.availability.setText(driver.getAvailability());
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }
}
