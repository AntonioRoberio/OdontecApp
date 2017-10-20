package com.des.odontec.equipe.odontec.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.Model.Patologia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.Model.VersaoDados;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Antonio on 18/10/2017.
 */

public class PatologiaDao {
    private DatabaseReference databaseReference=ConfiguracaoFirebaseDao.refernciaBancoFirebase();
    private SQLiteDatabase banco;
    private BDSqlieDao bd;
    private Context context;

    public PatologiaDao(Context context){
        this.context=context;
        bd=new BDSqlieDao(context);
        banco=bd.getWritableDatabase();
    }

//
    public void pegarDadosBD() {
        databaseReference.child("versoes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VersaoDados versaoDados = dataSnapshot.getValue(VersaoDados.class);
                ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(context);
                if (!versaoDados.getPatologia().toString().equals(arquivosDePreferencia.retornoVersao("patologia"))) {
                    int contador = Integer.parseInt(arquivosDePreferencia.retornoVersao("contPatol"));
                    if (contador != 0) {
                        for (int i = contador; i >= 1; i--) {
                            String id = String.valueOf(i);
                            banco.delete("patologias", "_id = ?", new String[]{id});
                        }

                    }
                    pegarPatologias();
                    arquivosDePreferencia.salvarVersaoPatologia(versaoDados.getPatologia().toString(), "verPat");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void pegarPatologias(){
        databaseReference.child("patologias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContentValues contentValues=new ContentValues();
                int cont=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    cont++;
                    Patologia p=dataSnapshot1.getValue(Patologia.class);
                    contentValues.put("tipoPatologia",p.getTipoPatologia());
                    contentValues.put("_id",String.valueOf(cont));
                    banco.insert("listaPatologias",null,contentValues);
                }
                ArquivosDePreferencia arquivosDePreferencia = new ArquivosDePreferencia(context);
                arquivosDePreferencia.salvarVersaoPatologia(new String(cont + ""), "cont");
                banco.close();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Patologia> listarPatologias(){
        ArrayList<Patologia> patologias=new ArrayList<>();

        String[] colunas={"_id","tipoPatologia"};
        Cursor cursor=banco.query("listaPatologias",colunas,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                Patologia patologia=new Patologia();
                patologia.setId(cursor.getString(0));
                patologia.setTipoPatologia(cursor.getString(1));
                patologias.add(patologia);
            }while (cursor.moveToNext());
        }

        banco.close();
        return patologias;
    }
    //

    //-----------------------------------------------------------------
    public void salvarBD(Patologia patologia) {
        DatabaseReference dados = ConfiguracaoFirebaseDao.refernciaBancoFirebase();
        dados.child("patologias").child(patologia.getId()).setValue(patologia.patologia());
    }
}
