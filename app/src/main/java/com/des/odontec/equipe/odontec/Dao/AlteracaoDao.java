package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.Model.Alteracao;
import com.des.odontec.equipe.odontec.Model.VersaoDados;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Antonio on 24/09/2017.
 */

public class AlteracaoDao {
    private SQLiteDatabase banco;
    private DatabaseReference databaseReference = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
    private BDSqlieDao bdAlteracao;
    private Context context;

    public AlteracaoDao() {

    }


    public AlteracaoDao(Context context) {
        this.context = context;
        bdAlteracao = new BDSqlieDao(context, "listaAlteracao");
        banco = bdAlteracao.getWritableDatabase();
    }

    public void pegarDadosBD() {

        databaseReference.child("versoes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VersaoDados versaoDados = dataSnapshot.getValue(VersaoDados.class);
                ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(context);
                if (!versaoDados.getAlteracao().toString().equals(String.valueOf(arquivosDePreferencia.retornoVersao("alteracao")))) {
                    pegarDadosBD2();
                    arquivosDePreferencia.salvarVersoaAlter(1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void pegarDadosBD2() {
        databaseReference.child("alteracao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContentValues contentValues = new ContentValues();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Alteracao alteracao = d.getValue(Alteracao.class);
                    contentValues.put("tipoAlter", alteracao.getTipoAlteracao());
                    banco.insert("listaAlteracao", null, contentValues);
                }

                banco.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Alteracao> listarAlteracoes() {
        ArrayList<Alteracao> alteracaos = new ArrayList<>();
        String[] coluna = {"_id", "tipoAlter"};
        Cursor cursor = banco.query("listaAlteracao", coluna, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Alteracao alteracao = new Alteracao();
                alteracao.setId(String.valueOf(cursor.getLong(0)));
                alteracao.setTipoAlteracao(cursor.getString(1));
                alteracaos.add(alteracao);
            } while (cursor.moveToNext());

        }

        banco.close();
        return alteracaos;
    }

}