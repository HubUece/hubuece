package com.example.vroch.hubuece.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.vroch.hubuece.data.provider.DbHelper;

import java.util.List;

/**
 * Created by vroch on 03/02/2016.
 */
public interface Dao<T> {

     long save(T item);
     long delete(T item);
     long update(T item);
     List<T> getAll();

}
