package com.actiknow.triburg.model;

/**
 * Created by l on 15/02/2017.
 */

public class ShoppingBagProduct {
    int id;
    String image, name, reference, size, price, color;

    public ShoppingBagProduct (int id, String image, String name, String reference, String size, String price, String color){
        this.id=id;
        this.image=image;
        this.name = name;
        this.reference = reference;
        this.size = size;
        this.price = price;
this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getReference () {
        return reference;
    }

    public void setReference (String reference) {
        this.reference = reference;
    }

    public String getSize () {
        return size;
    }

    public void setSize (String size) {
        this.size = size;
    }

    public String getPrice () {
        return price;
    }

    public void setPrice (String price) {
        this.price = price;
    }

    public String getColor () {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }
}
