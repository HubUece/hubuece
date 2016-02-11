package com.example.vroch.hubuece.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vroch.hubuece.data.model.MapLocation;
import com.example.vroch.hubuece.data.provider.DbContract;
import com.example.vroch.hubuece.data.provider.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class MapLocationDao implements Dao<MapLocation> {

    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public MapLocationDao(Context context){

        dbHelper = DbHelper.getInstance(context);


    }

    @Override
    public long save(MapLocation item) {

        try{

            ContentValues values = new ContentValues();

            values.put(DbContract.MapTable.LOCALIZACAO_ID,item.getLocalizacao_id());
            values.put(DbContract.MapTable.NOME,item.getNome());
            values.put(DbContract.MapTable.DESCRICAO,item.getDescricao());
            values.put(DbContract.MapTable.CONTATO,item.getContato());
            values.put(DbContract.MapTable.LATITUDE,item.getLatitude());
            values.put(DbContract.MapTable.LONGITUDE,item.getLongitude());
            values.put(DbContract.MapTable.IMAGEM,item.getImagem());

            database = dbHelper.getWritableDatabase();

            return database.insertWithOnConflict(DbContract.MAP_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public long delete(MapLocation item) {
        return 0;
    }

    @Override
    public long update(MapLocation item) {
        return 0;
    }

    @Override
    public List<MapLocation> getAll() {

        List<MapLocation> mapLocationsList = new ArrayList<>();

        try{

            database = dbHelper.getReadableDatabase();

            String[] columns = {
                    DbContract.MapTable.LOCALIZACAO_ID,
                    DbContract.MapTable.NOME,
                    DbContract.MapTable.DESCRICAO,
                    DbContract.MapTable.CONTATO,
                    DbContract.MapTable.IMAGEM,
                    DbContract.MapTable.LATITUDE,
                    DbContract.MapTable.LONGITUDE,

            };

            Cursor cursor = database.query(DbContract.MAP_TABLE_NAME,columns,null,null,null,null,null);

            while (cursor.moveToNext()){

                MapLocation mapLocation = new MapLocation(cursor);
                mapLocationsList.add(mapLocation);
            }
            cursor.close();

        } catch (Exception e){
            e.printStackTrace();
        }


        return mapLocationsList;
    }
}
