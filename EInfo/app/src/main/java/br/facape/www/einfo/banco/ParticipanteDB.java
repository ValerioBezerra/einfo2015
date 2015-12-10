package br.facape.www.einfo.banco;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.facape.www.einfo.modelo.Participante;

public class ParticipanteDB {
    private SQLiteDatabase db;

    public ParticipanteDB(Context context) {
        db = new DBHelper(context).getWritableDatabase();
    }

    public List<Participante> getLista() {
        List<Participante> listaParticipantes = new ArrayList<Participante>();

        Cursor cursor = db.rawQuery("SELECT * FROM participantes", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Participante participante = new Participante();
                participante.setId(cursor.getInt(cursor.getColumnIndex("id")));
                participante.setNome(cursor.getString(cursor.getColumnIndex("nome")));

                listaParticipantes.add(participante);
                cursor.moveToNext();
            }
        }

        return listaParticipantes;
    }

    public void inserir(Participante participante) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", participante.getNome());

        db.beginTransaction();
        db.insert("participantes", null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void alterar(Participante participante) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", participante.getNome());

        db.beginTransaction();
        db.update("participantes", contentValues, " id = " + participante.getId(), null);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void excluir(int id) {
        db.beginTransaction();
        db.delete("participantes", " id = " + id, null);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


}
