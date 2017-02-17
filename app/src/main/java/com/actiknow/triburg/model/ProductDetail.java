package com.actiknow.triburg.model;

import java.util.ArrayList;

/**
 * Created by Admin on 17-02-2017.
 */

public class ProductDetail {
    int id;
    String name, info, price;
    ArrayList<String> images;

    public ProductDetail (int id, String name, String info, String price, ArrayList<String> images) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.price = price;
        this.images = images;
    }

    public ProductDetail () {
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

    public String getInfo () {
        return info;
    }

    public void setInfo (String info) {
        this.info = info;
    }

    public String getPrice () {
        return price;
    }

    public void setPrice (String price) {
        this.price = price;
    }

    public ArrayList<String> getImages () {
        return images;
    }

    public void setImages (ArrayList<String> images) {
        this.images = images;
    }
}
