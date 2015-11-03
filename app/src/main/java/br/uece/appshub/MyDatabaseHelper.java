package br.uece.appshub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.uece.appshub.cardapio.CardapioORM;
import br.uece.appshub.cardapio.ItemCardapioORM;
import br.uece.appshub.mapa.LugarORM;

/**
 * Created by stack on 20/10/15.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "appshubs";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LugarORM.CRIAR_TABELA);
        db.execSQL(CardapioORM.CRIAR_TABELA);
        db.execSQL(ItemCardapioORM.CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
