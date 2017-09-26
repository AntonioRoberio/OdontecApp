package com.des.odontec.equipe.odontec.Dao;


import android.content.ContentValues;
import android.content.Context;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.Model.Anestesico;
import com.des.odontec.equipe.odontec.Model.VersaoDados;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by Antonio on 18/09/2017.
 */

public class AnestesicoDao {

    private SQLiteDatabase banco;
    private DatabaseReference databaseReference = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
    private BDSqlieDao bdAnestesico;
   private Context context;

    public AnestesicoDao() {

    }

    public AnestesicoDao(Context context) {
        this.context=context;
        bdAnestesico = new BDSqlieDao(context,"listaAnestesicos");
        banco = bdAnestesico.getWritableDatabase();
    }



    public void pegarDadosBD(){

     databaseReference.child("versoes").addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             VersaoDados versaoDados=dataSnapshot.getValue(VersaoDados.class);
             ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(context);
             if(!versaoDados.getAnestesico().toString().equals(String.valueOf(arquivosDePreferencia.retornoVersao("anestesico")))){
                 pegarDadosBD2();
                 arquivosDePreferencia.salvarVersoaAnes(1);
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });

    }

    private void pegarDadosBD2() {
        databaseReference.child("anestesicos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContentValues contentValues = new ContentValues();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Anestesico anestesico = d.getValue(Anestesico.class);
                    contentValues.put("tipoAnes", anestesico.getTipoAnestesico());
                    banco.insert("listaAnestesicos", null, contentValues);
                }

                banco.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Anestesico> listarAnestesicos() {
        ArrayList<Anestesico> anestesicos = new ArrayList<>();
        String[] coluna = {"_id", "tipoAnes"};
        Cursor cursor = banco.query("listaAnestesicos", coluna, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Anestesico anes = new Anestesico();
                anes.setId(String.valueOf(cursor.getLong(0)));
                anes.setTipoAnestesico(cursor.getString(1));
                anestesicos.add(anes);

            } while (cursor.moveToNext());
        }
        banco.close();
        return anestesicos;
    }


}
