package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antonio on 19/09/2017.
 */

public class BDSqlieDao extends SQLiteOpenHelper {
    private static final String NOME_BD = "BancoNovo3";
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
        db.execSQL("create table quiz(_id text primary key ,pergunta text not null,respostaA text not null,respostaB text not null,respostaC text not null,respostaD text not null,respostaE text not null,altCorreta text not null);");
        db.execSQL("create table pacientes(_id integer primary key autoincrement,nome text not null,idade int not null,peso double not null,sexo text not null,dataDeAtendimento text not null,alteracao text not null,anestesico not null,qtdTubetes double not null);");
        db.execSQL("create table placar(_id integer primary key autoincrement,pontos text not null,acertos text not null,erros text not null,data text not null);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table listaAnestesicos");
        db.execSQL("drop table listaAlteracao");
        db.execSQL("drop table usuarios");
        db.execSQL("drop table patologias");
        db.execSQL("drop table quiz");
        db.execSQL("drop table pacientes");
        onCreate(db);
    }
}
