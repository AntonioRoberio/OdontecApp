package com.des.odontec.equipe.odontec.Dao;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.View.MainActivity_Login;
import com.des.odontec.equipe.odontec.View.ResetSenha;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Antonio on 06/10/2017.
 */

public class UsuarioDaoFirebase {

    private FirebaseUser user;
    private FirebaseAuth auth;
    Context context;
    public UsuarioDaoFirebase(Context context){
        this.context=context;
    }

    public UsuarioDaoFirebase(){

    }

    public void atualizarSe(String atual, final Usuario usuario) {
        FirebaseAuth auth = ConfiguracaoFirebaseDao.autenticarDados();

        user = auth.getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail().toString(),
                Criptografia.md5(atual.toString()));
        user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(context);
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePassword(usuario.getSenha().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                arquivosDePreferencia.statusDeVerificacao("Senha Alterada com sucesso");
                            } else {
                                arquivosDePreferencia.statusDeVerificacao("Erro ao Alterar senha");
                            }
                        }
                    });
                } else {
                    arquivosDePreferencia.statusDeVerificacao("Erro ao alterar senha. Confira sua senha atual.");
                }
            }
        });
    }


    public void resetar(Usuario usuario) {
            auth= ConfiguracaoFirebaseDao.autenticarDados();
            auth.sendPasswordResetEmail(usuario.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(context);
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   arquivosDePreferencia.statusDeVerificacao("sucesso");
                }else{
                    arquivosDePreferencia.statusDeVerificacao("erro");
                }
            }
        });
    }


}
