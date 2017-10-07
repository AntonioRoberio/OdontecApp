package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;
import android.support.annotation.NonNull;



import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.View.AtualizarSenha;
import com.des.odontec.equipe.odontec.View.CadastrarUsuario;
import com.des.odontec.equipe.odontec.View.DeletarConta;
import com.des.odontec.equipe.odontec.View.ResetSenha;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


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

    public void cdtUsuario(final Usuario usuario, final CadastrarUsuario cadastrarUsuario) {

            usuarioDao.cadastraUsuario(usuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                CadastrarUsuario cadastrar=cadastrarUsuario;
                if(task.isSuccessful()){
                   usuarioDao.salvarBD(usuario);

                    cadastrar.cadastraUsuario("Usuário cadastrado com sucesso");
                }else {
                    String mensagemErro = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        mensagemErro = "Senha fraca. digite uma senha contendo no mínimo 6 caracteres.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        mensagemErro = "Endereço de E-MAIL invalido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        mensagemErro = "Este E-MAIL já está sendo usado";
                    } catch (Exception e) {
                        mensagemErro = "Erro ao se cadastrar";
                        e.printStackTrace();
                    }
                    cadastrar.cadastraUsuario(mensagemErro);
                }
            }
        });
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

    public void apagarConta(String senha, final DeletarConta deletarConta) {
        usuarioDao.deletar(senha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    DeletarConta deletar=deletarConta;
                    deletar.apagar();
                }
            }
        });
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
