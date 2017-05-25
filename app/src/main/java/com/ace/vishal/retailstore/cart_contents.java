package com.ace.vishal.retailstore;

/**
 * Created by Hp on 1/10/2017.
 */
public class cart_contents  {
    private String title,quantity,price,availability;
    private int image;

    public cart_contents() {
    }

    public cart_contents(String title, String availability,String price, int image,String quantity ) {
        this.title = title;
        this.availability=availability;
        this.quantity = quantity;
        this.image = image;
        this.price=price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice(){return price;}

    public void setPrice(String price){this.price=price;}
    public String getAvailability(){return availability;}

    public void setAvailability(String availability){this.availability=availability;}


}
