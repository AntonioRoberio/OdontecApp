package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.UsuarioDao;
import com.des.odontec.equipe.odontec.Model.Usuario;


/**
 * Created by Antonio on 31/08/2017.
 */

public class UsuarioController {

    private Usuario usuarioRef;
    private UsuarioDao usuarioDao;

    public UsuarioController(){
        usuarioRef =new Usuario();
        usuarioDao=new UsuarioDao();
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

    public void pegarDados(Context context){
         usuarioDao.pegarDados(context);
    }

    public String[] renoDados(Context context){
        String[] valores= usuarioDao.renovarDados(context);
        return valores;
    }

    public void fazerLgoutSistema(){
        usuarioDao.fazerLgout();
    }


}
