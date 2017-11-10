package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.Model.Paciente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Antonio on 05/11/2017.
 */

public class PacienteDao {
    private Context context;
    private SQLiteDatabase bd;
    private BDSqlieDao bdSqlieDao;
    private FirebaseAuth auth;
    private DatabaseReference reference;

    public PacienteDao(Context context) {
        this.context = context;
        bdSqlieDao = new BDSqlieDao(context);
        bd = bdSqlieDao.getWritableDatabase();
        auth = ConfiguracaoFirebaseDao.autenticarDados();
        reference = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
    }

    public void salvarPaciente(Paciente paciente) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", paciente.getNome());
        contentValues.put("idade", paciente.getIdade());
        contentValues.put("peso", paciente.getPeso());
        contentValues.put("sexo", paciente.getSexo());
        contentValues.put("dataDeAtendimento", paciente.getDataDeAtendimento());
        contentValues.put("alteracao", paciente.getAlteracao());
        contentValues.put("anestesico", paciente.getAnestesico());
        contentValues.put("qtdTubetes", paciente.getQtdTubetes());
        bd.insert("pacientes", null, contentValues);
        bd.close();
        salvarDados(paciente);
    }

    public void atualizarDadosPaciente(Paciente paciente, String chamada) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", paciente.getNome());
        contentValues.put("idade", paciente.getIdade());
        contentValues.put("peso", paciente.getPeso());
        contentValues.put("sexo", paciente.getSexo());
        contentValues.put("dataDeAtendimento", paciente.getDataDeAtendimento());
        contentValues.put("alteracao", paciente.getAlteracao());
        contentValues.put("anestesico", paciente.getAnestesico());
        contentValues.put("qtdTubetes", paciente.getQtdTubetes());
        bd.update("pacientes", contentValues, "_id = ?", new String[]{"" + paciente.getId()});
        bd.close();
        if (chamada.equals("finais")) {
            salvarDados(paciente);
        }

    }

    public void deletarPaciente(int id) {
        bd.delete("pacientes", "_id = ?", new String[]{"" + id});
    }

    public ArrayList<Paciente> exibirDadosPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String[] colunas = {"_id", "nome", "idade", "peso", "sexo", "dataDeAtendimento", "alteracao", "anestesico", "qtdTubetes"};
        Cursor cursor = bd.query("pacientes", colunas, null, null, null, null, "nome ASC");

        if (cursor.moveToFirst()) {
            do {
                Paciente paciente = new Paciente();
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
            } while (cursor.moveToNext());
        }

        return pacientes;
    }

    public void salvarDados(Paciente paciente) {

        FirebaseUser user = auth.getCurrentUser();
        String idUser = user.getUid();
        /*
        char[] pegaId = idUser.toCharArray();
        String id = "";
        for (int i = 0; i < 16; i++) {
            id += pegaId[i];
        }
        */
        char c = ' ';
        char[] pgData = paciente.getDataDeAtendimento().toCharArray();
        String novaData = "";
        for (int i = 0; i < pgData.length; i++) {
            c = pgData[i];
            String at = String.valueOf(c);
            if (!at.equals("/")) {
                novaData += at;
            }
        }
        reference.child("user").child(idUser).child("pacientes").child(paciente.getNome() + " " + novaData).setValue(paciente.dados());
    }
}
