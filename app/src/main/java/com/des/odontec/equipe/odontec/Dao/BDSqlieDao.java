package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antonio on 19/09/2017.
 */

public class BDSqlieDao extends SQLiteOpenHelper {
    private static final String NOME_BD = "Bancoteste";
    private static final int VERSAO = 1;
    public BDSqlieDao(Context context) {
        super(context, NOME_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table listaAnestesicos(_id text primary key ,tipoAnes text not null);");
        db.execSQL("create table listaAlteracao(_id text primary key ,tipoAlter text not null);");
        db.execSQL("create table usuarios(_id text primary key ,nome text not null,estado text not null,cidade text not null,email text not null);");
        db.execSQL("create table listaPatologias(_id text primary key ,tipoPatologia text not null,tipoTratamento text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table listaAnestesicos");
        db.execSQL("drop table listaAlteracao");
        db.execSQL("drop table usuarios");
        db.execSQL("drop table patologias");
        onCreate(db);
    }
}
