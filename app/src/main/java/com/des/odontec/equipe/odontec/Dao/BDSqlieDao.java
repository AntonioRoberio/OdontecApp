package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antonio on 19/09/2017.
 */

public class BDSqlieDao extends SQLiteOpenHelper {
    private static final String NOME_BD = "BancoTeste";
    private static final int VERSAO = 1;
    public BDSqlieDao(Context context,String nomeTable) {
        super(context, NOME_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table listaAnestesicos(_id integer primary key autoincrement ,tipoAnes text not null);");
        db.execSQL("create table listaAlteracao(_id integer primary key autoincrement ,tipoAlter text not null);");
        db.execSQL("create table usuarios(_id text primary key  ,nome text not null,estado text not null,cidade text not null,email text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table listaAnestesicos");
        db.execSQL("drop table listaAlteracao");
        db.execSQL("drop table usuarios");
        onCreate(db);
    }
}
