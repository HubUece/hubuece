package com.example.vroch.hubuece.data.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vroch on 12/12/2015.
 */
public class WeekDay implements Serializable {

    public String dayName;
    public String date;
    public ArrayList<MenuItem> menu;

    public WeekDay(String dayName,String date,ArrayList<MenuItem> menu) {

        this.dayName = dayName;
        this.date = date;
        this.menu = menu;

    }
}
