package com.des.odontec.equipe.odontec.Dao;

import com.des.odontec.equipe.odontec.Model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioDao {


    public UsuarioDao(){
    }
    //método que faz o cadastro dos dados de um novo usuário no banco de dados do firebase
    public void salvarBD(Usuario usuario){
        DatabaseReference dados=ConfiguracaoFirebase.refernciaBancoFirebase();
        dados.child("user").child(String.valueOf(usuario.getId())).setValue(usuario);
    }

    public void fazerLgout(){
        FirebaseAuth sair=ConfiguracaoFirebase.autenticarDados();
        sair.signOut();
    }

}
