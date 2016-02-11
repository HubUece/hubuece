package com.example.vroch.hubuece.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper sInstance = null;

    public static final String DB_NAME = "main_database";
    public static final int DB_VERSION = 1;

    public static synchronized DbHelper getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new DbHelper(context);
        }

        return sInstance;
    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.MapTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
