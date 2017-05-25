package com.ace.vishal.retailstore;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 9/13/2016.
 */
public class VegetableActivity extends AppCompatActivity{
    // Log tag
    private static final String TAG = VegetableActivity.class.getSimpleName();
    // Movies json url
    private static final String url = "http://sameepmago.16mb.com/vegetables.json";
    private ProgressDialog pDialog;
    private List<Vegetables> movieList = new ArrayList<Vegetables>();
    private ListView listView;
    private CustomListAdapter adapter;
    int i,position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegetable_layout);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList,null);
        listView.setAdapter(adapter);
        adapter.setButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i = 0; i < listView.getChildCount(); i++) {
                    if (v == listView
                            .getChildAt(i).findViewById(R.id.cart)) {
                         position=i;
                    }
                }
                final Dialog dialog = new Dialog(VegetableActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.quantity);
                // Set dialog title
                final NumberPicker quantity= (NumberPicker)dialog.findViewById(R.id.numberPicker);
                final Button addcart=(Button)dialog.findViewById(R.id.addcart);
                addcart.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        if(v==addcart){
                            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
                            Constants.name[Constants.counter++]=movieList.get(position).getTitle().toString();
                            Constants.price[Constants.counter1++]=movieList.get(position).getYear().toString();
                            Constants.availability[Constants.counter2++]=movieList.get(position).getRating().toString();
                            Constants.quantity[Constants.counter3++]=quantity.getValue();
                            Log.d("",Constants.name[0]);
                            Log.d("",Constants.price[0]);
                            Log.d("",Constants.availability[0]);
                            Intent start=new Intent(VegetableActivity.this,cart.class);
                            startActivity(start);
                        }
                    }
                });
                quantity.setMinValue(0);
                //Specify the maximum value/number of NumberPicker
                quantity.setMaxValue(10);

                //Gets whether the selector wheel wraps when reaching the min/max value.
                quantity.setWrapSelectorWheel(true);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Vegetables movie = new Vegetables();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(obj.getString("rating"));
                                movie.setYear(obj.getString("releaseYear"));

                                // Genre is json array
                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}