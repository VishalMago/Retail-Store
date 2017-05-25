package com.ace.vishal.retailstore;

import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Hp on 9/13/2016.
 */
public class Vegetables {
    private String title, thumbnailUrl;
    private String year;
    private String rating;

    public Vegetables() {
    }

    public Vegetables(String name, String thumbnailUrl, String year, String rating) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}