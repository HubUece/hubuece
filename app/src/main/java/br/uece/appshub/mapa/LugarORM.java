package br.uece.appshub.mapa;

/**
 * Created by stack on 22/10/15.
 */
public class LugarORM {

    public static final String TAB_NOME = "lugares";
    public static final String COL_NOME = "nome";
    public static final String COL_LAT = "latitude";
    public static final String COL_LONG = "longitude";
    public static final String COL_DESC = "descricao";
    public static final String COL_ID = "id";
    public static final String COL_CONT = "contato";
    public static final String CRIAR_TABELA = "CREATE TABLE IF NOT EXISTS lugares (id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT NOT NULL, latitude FLOAT NOT NULL, longitude FLOAT NOT NULL, descricao TEXT, contato INTEGER NOT NULL);";

}
