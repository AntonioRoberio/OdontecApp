package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.Model.PlacarQuiz;

import java.util.ArrayList;


/**
 * Created by Antonio on 12/11/2017.
 */

public class PlacarQuizDao {
    private Context context;
    private SQLiteDatabase bd;
    private BDSqlieDao bdSqlieDao;

    public PlacarQuizDao(Context context) {
        this.context = context;
        bdSqlieDao = new BDSqlieDao(context);
        bd = bdSqlieDao.getWritableDatabase();
    }

    public void salvarPaciente(PlacarQuiz placarQuiz) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pontos", placarQuiz.getPontos());
        contentValues.put("acertos", placarQuiz.getAcertos());
        contentValues.put("erros", placarQuiz.getErros());
        contentValues.put("nome",placarQuiz.getNome());
        bd.insert("placar", null, contentValues);
        bd.close();
    }

    public ArrayList<PlacarQuiz> exibirDadosPlacar() {
        ArrayList<PlacarQuiz> placar = new ArrayList<>();
        String[] colunas = {"_id","nome","pontos", "acertos", "erros"};
        Cursor cursor = bd.query("placar", colunas, null, null, null, null, "pontos DESC");

        if (cursor.moveToFirst()) {
            do {
                PlacarQuiz placarQuiz = new PlacarQuiz();
                placarQuiz.setId(cursor.getInt(0));
                placarQuiz.setNome(cursor.getString(1));
                placarQuiz.setPontos(cursor.getString(2));
                placarQuiz.setAcertos(cursor.getString(3));
                placarQuiz.setErros(cursor.getString(4));
                placar.add(placarQuiz);
            } while (cursor.moveToNext());
        }

        return placar;
    }
}
