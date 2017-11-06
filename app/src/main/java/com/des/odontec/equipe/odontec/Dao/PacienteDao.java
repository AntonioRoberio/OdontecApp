package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.Model.Paciente;

import java.util.ArrayList;

/**
 * Created by Antonio on 05/11/2017.
 */

public class PacienteDao {
    private Context context;
    private SQLiteDatabase bd;
    private BDSqlieDao bdSqlieDao;
    public PacienteDao(Context context){
        this.context=context;
        bdSqlieDao= new BDSqlieDao(context);
        bd=bdSqlieDao.getWritableDatabase();
    }

    public void salvarPaciente(Paciente paciente){
        ContentValues contentValues=new ContentValues();
        contentValues.put("nome",paciente.getNome());
        contentValues.put("idade",paciente.getIdade());
        contentValues.put("peso",paciente.getPeso());
        contentValues.put("sexo",paciente.getSexo());
        contentValues.put("dataDeAtendimento",paciente.getDataDeAtendimento());
        contentValues.put("alteracao",paciente.getAlteracao());
        contentValues.put("anestesico",paciente.getAnestesico());
        contentValues.put("qtdTubetes",paciente.getQtdTubetes());
        bd.insert("pacientes",null,contentValues);
        bd.close();
    }

    public void atualizarDadosPaciente(Paciente paciente){
        ContentValues contentValues=new ContentValues();
        contentValues.put("nome",paciente.getNome());
        contentValues.put("idade",paciente.getIdade());
        contentValues.put("peso",paciente.getPeso());
        contentValues.put("sexo",paciente.getSexo());
        contentValues.put("dataDeAtendimento",paciente.getDataDeAtendimento());
        contentValues.put("alteracao",paciente.getAlteracao());
        contentValues.put("anestesico",paciente.getAnestesico());
        contentValues.put("qtdTubetes",paciente.getQtdTubetes());
        bd.update("pacientes",contentValues,"_id = ?",new String[]{""+paciente.getId()});
        bd.close();
    }

    public void deletarPaciente(Paciente paciente){
        bd.delete("pacientes","_id = ?",new String[]{""+paciente.getId()});
    }

    public ArrayList<Paciente> exibirDadosPacientes(){
        ArrayList<Paciente> pacientes=new ArrayList<>();
        String[] colunas={"_id","nome","idade","peso","sexo","dataDeAtendimento","alteracao","anestesico","qtdTubetes"};
        Cursor cursor=bd.query("pacientes",colunas,null,null,null,null,"nome ASC");

        if(cursor.moveToFirst()){
            do{
                Paciente paciente=new Paciente();
                paciente.setId(cursor.getInt(0));
                paciente.setNome(cursor.getString(1));
                paciente.setIdade(cursor.getInt(2));
                paciente.setPeso(cursor.getDouble(3));
                paciente.setSexo(cursor.getString(4));
                paciente.setDataDeAtendimento(cursor.getString(5));
                paciente.setAlteracao(cursor.getString(6));
                paciente.setAnestesico(cursor.getString(7));
                paciente.setQtdTubetes(cursor.getDouble(8));
                pacientes.add(paciente);
            }while (cursor.moveToNext());
        }

        return pacientes;
    }
}
