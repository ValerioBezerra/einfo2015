package br.facape.www.einfo.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static String NOME_BANCO = "einfo";
    private static int VERSAO_BANCO  = 2;


    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("EINFO", "Passou 1");

        db.execSQL(" CREATE TABLE participantes ( " +
                   "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                   "   nome VARCHAR(50)" +
                   " ); ");

        Log.i("EINFO", "Passou 2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2 && newVersion >= 2) {
            Log.i("EINFO", "Versão 2");
            db.execSQL("INSERT INTO participantes VALUES (NULL, 'Valério Bezerra')");
            db.execSQL("INSERT INTO participantes VALUES (NULL, 'Neri')");
        }
    }
}
