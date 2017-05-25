package com.ace.vishal.retailstore;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 1/10/2017.
 */
public class cart extends AppCompatActivity {
    private List<cart_contents> cartList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter mAdapter;
    private TextView noitems;
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.cart);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        noitems=(TextView)findViewById(R.id.noitems);
        mAdapter = new CartAdapter(cartList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    private void prepareMovieData() {
        if(Constants.counter<0){
            noitems.setVisibility(View.VISIBLE);
            Log.d("","Not entered");
        }
        else {
            noitems.setVisibility(View.INVISIBLE);
            for(int i=0;i<Constants.counter;i++) {
                Log.d("","enter");
                cart_contents movie = new cart_contents(Constants.name[i], Constants.availability[i], Constants.price[i], R.drawable.about, Constants.quantity[i] + "kg");
                cartList.add(movie);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
