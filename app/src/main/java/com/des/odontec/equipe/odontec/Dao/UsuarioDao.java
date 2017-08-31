package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.des.odontec.equipe.odontec.Model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioDao {
    private String pegarSenhaAtual;

    public UsuarioDao(){
    }
    //método que faz o cadastro dos dados de um novo usuário no banco de dados do firebase
    public void salvarBD(Usuario usuario){
        DatabaseReference dados=ConfiguracaoFirebase.refernciaBancoFirebase();
        dados.child("user").child(String.valueOf(usuario.getId())).setValue(usuario);
    }

    public void pegarDados(final Context context){
        DatabaseReference reference=ConfiguracaoFirebase.refernciaBancoFirebase();
        FirebaseAuth auth=ConfiguracaoFirebase.autenticarDados();
        FirebaseUser user=auth.getCurrentUser();
        String id=user.getUid().toString();
        reference.child("user").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             Usuario u=dataSnapshot.getValue(Usuario.class);
                pegarSenhaAtual=u.getSenha().toString();
                SharedPreferences sharedPreferences=context.getSharedPreferences("PREFERENCIA",context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Nome",u.getNome().toString());
                editor.putString("Estado",u.getEstado().toString());
                editor.putString("Cidade",u.getCidade().toString());
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String[] renovarDados(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("PREFERENCIA",context.MODE_PRIVATE);
        String[] valores=new String[3];
        valores[0]=sharedPreferences.getString("Nome","");
        valores[1]=sharedPreferences.getString("Estado","");
        valores[2]=sharedPreferences.getString("Cidade","");

        return valores;
    }



    public void fazerLgout(){
        FirebaseAuth sair=ConfiguracaoFirebase.autenticarDados();
        sair.signOut();
    }

}
