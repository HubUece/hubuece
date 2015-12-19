package com.example.vroch.hubuece.data.model;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by vroch on 14/12/2015.
 */
public class MenuItem implements Serializable {

    public String name;
    public int image;

    public MenuItem(String itemName,int itemImage) {

        this.name = itemName;
        this.image = itemImage;
    }


}
