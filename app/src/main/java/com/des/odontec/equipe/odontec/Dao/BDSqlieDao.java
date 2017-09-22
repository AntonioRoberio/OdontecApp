package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antonio on 19/09/2017.
 */

public class BDSqlieDao extends SQLiteOpenHelper {
    private static final String NOME_BD = "Anestesicos";
    private static final int VERSAO = 2;

    public BDSqlieDao(Context context) {
        super(context, NOME_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table listaAnestesicos(_id integer primary key autoincrement ,tipoAnes text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table listaAnestesicos;");
        onCreate(db);
    }
}
