package com.actiknow.triburg.model;

/**
 * Created by Admin on 15-02-2017.
 */

public class Product {
    int id, price, offer_price;
    boolean is_offer, in_wishlist;
    String name, image, category;

    public Product (int id, int price, int offer_price, boolean is_offer, boolean in_wishlist, String name, String image, String category) {
        this.id = id;
        this.price = price;
        this.offer_price = offer_price;
        this.is_offer = is_offer;
        this.in_wishlist = in_wishlist;
        this.name = name;
        this.image = image;
        this.category = category;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public int getPrice () {
        return price;
    }

    public void setPrice (int price) {
        this.price = price;
    }

    public int getOffer_price () {
        return offer_price;
    }

    public void setOffer_price (int offer_price) {
        this.offer_price = offer_price;
    }

    public boolean is_offer () {
        return is_offer;
    }

    public void setIs_offer (boolean is_offer) {
        this.is_offer = is_offer;
    }

    public boolean isIn_wishlist () {
        return in_wishlist;
    }

    public void setIn_wishlist (boolean in_wishlist) {
        this.in_wishlist = in_wishlist;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }
}
