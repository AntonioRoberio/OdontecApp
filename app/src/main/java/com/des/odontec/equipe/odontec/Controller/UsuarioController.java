package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;
import android.support.annotation.NonNull;



import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.View.AtualizarSenha;
import com.des.odontec.equipe.odontec.View.ResetSenha;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioController{

    private Usuario usuarioRef;
    private UsuarioDao usuarioDao;
    public UsuarioController() {
        usuarioRef = new Usuario();
        usuarioDao = new UsuarioDao();

    }

    public void cdtUsuario(Usuario usuario) {
        usuarioRef.setId(usuario.getId());
        usuarioRef.setNome(usuario.getNome());
        usuarioRef.setIdade(usuario.getIdade());
        usuarioRef.setEstado(usuario.getEstado());
        usuarioRef.setCidade(usuario.getCidade());
        usuarioRef.setEmail(usuario.getEmail());
        usuarioRef.setSenha(usuario.getSenha());
        usuarioRef.setSexo(usuario.getSexo());
        usuarioDao.salvarBD(usuarioRef);
    }

    public void pegarDados(Context context) {
        usuarioDao.pegarDados(context);
    }

    public String[] renoDados(Context context) {
        String[] valores = usuarioDao.renovarDados(context);
        return valores;
    }

    public void atualizarDados(Usuario usuario) {
        usuarioRef.setNome(usuario.getNome());
        usuarioRef.setEstado(usuario.getEstado());
        usuarioRef.setCidade(usuario.getCidade());
        usuarioDao.upDados(usuarioRef);
    }

    public void apagarConta() {
        usuarioDao.deletar();
    }


    public void fazerLgoutSistema() {
        usuarioDao.fazerLgout();
    }

    public void atualizarSenha(String atual,Usuario usuario,final AtualizarSenha atualizarSenha){
        UsuarioDao usuarioDao=new UsuarioDao();
        usuarioDao.atualizarSe(atual,usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                AtualizarSenha atSenha=atualizarSenha;
                if(task.isSuccessful()){
                  atSenha.atualizarSe("Senha Alterada com sucesso");
                }else{
                    atSenha.atualizarSe("Erro ao alterar senha. Confira sua senha atual.");
                }
            }
        });
    }

    public void resetSenha(Usuario usuario,final ResetSenha resetSenha){
        UsuarioDao usuarioDao=new UsuarioDao();
        usuarioDao.resetar(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ResetSenha rest=resetSenha;
                if (task.isSuccessful()){
                    rest.resetar("Um E-mail foi enviado para você. Confira sua caixa de entrada!");
                }else{
                    rest.resetar("Erro ao enviar E-mail para reset de senha");
                }
            }
        });

    }

}
