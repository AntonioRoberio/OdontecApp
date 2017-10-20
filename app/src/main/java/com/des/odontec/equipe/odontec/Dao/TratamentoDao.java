package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.Model.Tratamento;
import com.des.odontec.equipe.odontec.Model.VersaoDados;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Antonio on 20/10/2017.
 */

public class TratamentoDao {

    private DatabaseReference databaseReference=ConfiguracaoFirebaseDao.refernciaBancoFirebase();
    private SQLiteDatabase banco;
    private BDSqlieDao bd;
    private Context context;

    public TratamentoDao(Context context){
        this.context=context;
        bd=new BDSqlieDao(context);
        banco=bd.getWritableDatabase();
    }

    public void pegarDadosBD() {
        databaseReference.child("versoes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VersaoDados versaoDados = dataSnapshot.getValue(VersaoDados.class);
                ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(context);
                if (!versaoDados.getTratamento().toString().equals(arquivosDePreferencia.retornoVersao("tratamento"))) {
                    int contador = Integer.parseInt(arquivosDePreferencia.retornoVersao("contTrata"));
                    if (contador != 0) {
                        for (int i = contador; i >= 1; i--) {
                            String id = String.valueOf(i);
                            banco.delete("listaTratamento", "_id = ?", new String[]{id});
                        }

                    }
                    tratamentos();
                    arquivosDePreferencia.salvarVersaoTratamento(versaoDados.getTratamento().toString(), "verTrat");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void tratamentos(){
        databaseReference.child("tratamentos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContentValues contentValues=new ContentValues();
                int cont=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    cont++;
                    Tratamento t=dataSnapshot1.getValue(Tratamento.class);
                    contentValues.put("tipoTratamento",t.getTipoTratamento());
                    contentValues.put("_id",String.valueOf(cont));
                    banco.insert("listaTratamento",null,contentValues);
                }
                ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(context);
                arquivosDePreferencia.salvarVersaoTratamento(new String(cont + ""), "cont");
                banco.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Tratamento> listarTratamento(){
        ArrayList<Tratamento> tratamentos=new ArrayList<>();

        String[] colunas={"_id","tipoTratamento"};
        Cursor cursor=banco.query("listaTratamento",colunas,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                Tratamento tratamento=new Tratamento();
                tratamento.setId(cursor.getString(0));
                tratamento.setTipoTratamento(cursor.getString(1));
                tratamentos.add(tratamento);
            }while (cursor.moveToNext());
        }

        banco.close();
        return tratamentos;
    }
}
