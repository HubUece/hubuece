package com.example.vroch.hubuece.data.provider;

import android.net.Uri;
import android.provider.BaseColumns;


public class DbContract {

    public static final String AUTHORITY = "com.example.vroch.hubuece.data.provider.provider";

    public static final String MAP_TABLE_NAME   = "tb_map";



    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY + "/");
    public static final Uri MAP_TABLE_URI = Uri.withAppendedPath(BASE_URI, MAP_TABLE_NAME);


    public static final Uri MAP_TABLE_GROUP_BY_URI = Uri.withAppendedPath(BASE_URI,
            MAP_TABLE_NAME + "/groupBy/");


    public static class MapTable implements BaseColumns {

        public static final String LOCALIZACAO_ID   = "localizacao_id";
        public static final String NOME             = "nome";
        public static final String DESCRICAO        = "descricao";
        public static final String LATITUDE         = "latitude";
        public static final String LONGITUDE        = "longitude";
        public static final String CONTATO          = "contato";
        public static final String IMAGEM           = "imagem";
        public static final String DATA_REGISTRO    = "data_Registro";


        public static final String SQL_CREATE_TABLE = "CREATE TABLE " +
                MAP_TABLE_NAME + " (" +

                LOCALIZACAO_ID      + " INTEGER, " +
                NOME                + " TEXT, " +
                DESCRICAO           + " TEXT, " +
                LATITUDE            + " REAL, " +
                LONGITUDE           + " REAL, " +
                CONTATO             + " TEXT, " +
                IMAGEM              + " TEXT " + ");";

        public static final String SQL_DROP_IF_EXISTS = "DROP TABLE IF EXISTS "+MAP_TABLE_NAME;
        public static final String SQL_TRUNCATE = "TRUNCATE "+MAP_TABLE_NAME;
    }



}
