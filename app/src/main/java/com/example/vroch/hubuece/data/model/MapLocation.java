package com.example.vroch.hubuece.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.vroch.hubuece.data.provider.DbContract;

import org.json.JSONObject;

/**
 * Created by vroch on 21/01/2016.
 */
public class MapLocation extends ModelBase {

    private int localizacao_id;
    private String nome;
    private String descricao;
    private String contato;
    private String imagem;
    private Double latitude;
    private Double longitude;
    private String dataRegistro;



    public MapLocation(JSONObject location) {

        try {

            this.localizacao_id = location.getInt(DbContract.MapTable.LOCALIZACAO_ID);
            this.nome = location.getString(DbContract.MapTable.NOME);
            this.descricao = location.getString(DbContract.MapTable.DESCRICAO);
            this.contato = location.getString(DbContract.MapTable.CONTATO);
            this.imagem = location.getString(DbContract.MapTable.IMAGEM);
            this.latitude = location.getDouble(DbContract.MapTable.LATITUDE);
            this.longitude = location.getDouble(DbContract.MapTable.LONGITUDE);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public MapLocation(Cursor cursor) {

        try {

            this.localizacao_id = cursor.getInt(cursor.getColumnIndex(DbContract.MapTable.LOCALIZACAO_ID));
            this.nome = cursor.getString(cursor.getColumnIndex(DbContract.MapTable.NOME));
            this.descricao = cursor.getString(cursor.getColumnIndex(DbContract.MapTable.DESCRICAO));
            this.contato = cursor.getString(cursor.getColumnIndex(DbContract.MapTable.CONTATO));
            this.imagem = cursor.getString(cursor.getColumnIndex(DbContract.MapTable.IMAGEM));
            this.latitude = cursor.getDouble(cursor.getColumnIndex(DbContract.MapTable.LATITUDE));
            this.longitude = cursor.getDouble(cursor.getColumnIndex(DbContract.MapTable.LONGITUDE));

        } catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public ContentValues getValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put("localizao_id",getLocalizacao_id());
        contentValues.put("nome",getNome());
        contentValues.put("descricao",getDescricao());
        contentValues.put("contato",getContato());
        contentValues.put("imagem",getImagem());
        contentValues.put("latitude",getLatitude());
        contentValues.put("longitude",getLongitude());

        return contentValues;
    }

    public Double getLongitude() {
        return longitude;
    }

    public long getLocalizacao_id() {
        return localizacao_id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getContato() {
        return contato;
    }

    public String getImagem() {
        return imagem;
    }

    public Double getLatitude() {
        return latitude;
    }
}
