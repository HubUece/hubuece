package com.example.vroch.hubuece.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONObject;

import java.io.Serializable;


public abstract class ModelBase implements Serializable {
    public long mId;

    public ModelBase() {

    }

    public ModelBase(JSONObject json) {

    }

    public ModelBase(Cursor data) {
        mId = data.getLong(data.getColumnIndex(BaseColumns._ID));
    }

    public abstract ContentValues getValues();

}