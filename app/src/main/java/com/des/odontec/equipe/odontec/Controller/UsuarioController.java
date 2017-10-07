package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.ArquivosDePreferencia;
import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.Dao.UsuarioDaoFirebase;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.View.AtualizarSenha;


/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioController {

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

    public String atualizarSenha(Context context,String atual,Usuario usuario){

        UsuarioDaoFirebase usuarioDaoFirebase=new UsuarioDaoFirebase(context);
        usuarioDaoFirebase.atualizarSe(atual,usuario);
        ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(context);
        return arquivosDePreferencia.retornostatusDeVerificacao();
    }

    public void resetSenha(Usuario usuario,Context context){
        UsuarioDaoFirebase usuarioDaoFirebase=new UsuarioDaoFirebase(context);
        usuarioDaoFirebase.resetar(usuario);

    }


}
