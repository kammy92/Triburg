package com.actiknow.triburg.model;

import android.graphics.Bitmap;

/**
 * Created by Admin on 17-02-2017.
 */

public class UploadImage {
    Bitmap image;

    public UploadImage (Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage () {
        return image;
    }

    public void setImage (Bitmap image) {
        this.image = image;
    }
}
