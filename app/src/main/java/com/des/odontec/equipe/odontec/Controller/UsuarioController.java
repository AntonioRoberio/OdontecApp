package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;
import android.support.annotation.NonNull;


import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Dao.ConfiguracaoFirebaseDao;
import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.MD5Cripto.Criptografia;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.View.AtualizarDados;
import com.des.odontec.equipe.odontec.View.AtualizarSenha;
import com.des.odontec.equipe.odontec.View.CadastrarUsuario;
import com.des.odontec.equipe.odontec.View.DeletarConta;
import com.des.odontec.equipe.odontec.View.MainActivity_Login;
import com.des.odontec.equipe.odontec.View.ResetSenha;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioController {

    private Usuario usuarioRef;
    private UsuarioDao usuarioDao;
    private UsuarioDao usuarioDaoCont;

    public UsuarioController() {
        usuarioRef = new Usuario();
        usuarioDao = new UsuarioDao();

    }

    public UsuarioController(Context context) {
        usuarioDaoCont = new UsuarioDao(context);
    }

    public void cdtUsuario(final Usuario usuario, final CadastrarUsuario cadastrarUsuario) {

        usuarioDao.cadastraUsuario(usuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                CadastrarUsuario cadastrar = cadastrarUsuario;
                if (task.isSuccessful()) {
                    usuarioDao.salvarBD(usuario);
                    cadastrar.cadastraUsuario("Usuário cadastrado com sucesso");
                } else {
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

    public void pegarDados() {
        usuarioDaoCont.pegarDados();
    }

    public Usuario exibirDados() {
        Usuario usuario = usuarioDaoCont.listarDados();
        return usuario;
    }

    public void atualizarDados(Usuario usuario, String valor, final AtualizarDados atualizarDados) {
        usuarioRef.setNome(usuario.getNome());
        usuarioRef.setEstado(usuario.getEstado());
        usuarioRef.setCidade(usuario.getCidade());
        usuarioDao.upDados(usuarioRef,valor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    atualizarDados.atualizarDados("Dados alterados com sucesso.");
                }else{
                    atualizarDados.atualizarDados("Erro. Tente novamente mais tarde.");
                }
            }
        });
    }

    public void apagarConta(String senha, final DeletarConta deletarConta) {
        usuarioDao.deletar(senha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DeletarConta deletar = deletarConta;
                if (task.isSuccessful()) {
                    deletar.apagar("sucesso");
                    fazerLgoutSistema();
                }else{
                    deletar.apagar("Erro na exclusão da conta. Confira sua senha.");
                }
            }
        });
    }


    public void fazerLgoutSistema() {
        usuarioDao.fazerLgout();
    }

    public void atualizarSenha(String atual, final Usuario usuario, final AtualizarSenha atualizarSenha,final String valor,final String valor2) {
        final UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.atualizarSe(atual, usuario,valor2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                AtualizarSenha atSenha = atualizarSenha;
                if (task.isSuccessful()) {
                    atSenha.atualizarSe("Senha Alterada com sucesso");
                    usuarioDao.upDados(usuario,valor);
                } else {
                    atSenha.atualizarSe("Erro ao alterar senha. Confira sua senha atual.");
                }
            }
        });
    }

    public void resetSenha(Usuario usuario, final ResetSenha resetSenha, final Context context) {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.resetar(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ResetSenha rest = resetSenha;
                Preferencias arquivosDePreferencia = new Preferencias(context);
                if (task.isSuccessful()) {
                    arquivosDePreferencia.alterSenha("true");
                    rest.resetar("Um E-mail foi enviado para você. Confira sua caixa de entrada!");
                } else {
                    rest.resetar("Erro ao enviar E-mail para reset de senha");
                }
            }
        });

    }

    public void logarOdontec(Usuario usuario, final MainActivity_Login login) {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.logar(usuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
                FirebaseUser us = aut.getCurrentUser();
                if (task.isSuccessful()) {
                    login.autenticarUsuario(us, "Seja bem vindo");

                } else {
                    login.autenticarUsuario(us, "Erro ao tentar logar");
                }
            }
        });
    }

    public void loginOdontec(final Usuario usuario, final MainActivity_Login login, final Context context) {
        final UsuarioDao usuarioDao = new UsuarioDao();

        usuarioDao.logar(usuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseAuth aut = ConfiguracaoFirebaseDao.autenticarDados();
                FirebaseUser us = aut.getCurrentUser();
                Preferencias arquivosDePreferencia = new Preferencias(context);
                if (task.isSuccessful()) {
                   login.autenticarUsuario(us, "Seja bem vindo");
                    arquivosDePreferencia.alterSenha("false");
                    usuario.setSenha(Criptografia.md5(usuario.getSenha()));
                    usuarioDao.upDados(usuario,"senha");
                    usuarioDao.atualizarSe(usuario.getSenha(), usuario,"senha");

                } else {
                    usuario.setSenha(Criptografia.md5(usuario.getSenha()));
                    logarOdontec(usuario, login);
                }
            }
        });
    }

    public void salvaDadosResesSociais(FirebaseUser user){
        usuarioDao.salvarDadosRedesSociais(user);
    }

}
