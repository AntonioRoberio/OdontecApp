package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.ArquivosDePreferencia;

/**
 * Created by Antonio on 24/09/2017.
 */

public class PreferenciasController {
    Context context;

    public PreferenciasController(Context context){
        this.context=context;
    }

    ArquivosDePreferencia arquivosDePreferencia=new ArquivosDePreferencia(context);

    public void conteUsuario(int valor){
    }

    public void conteUsuarioRetono(){

    }

    public void salvarVersoaAnes(int versao){
        arquivosDePreferencia.salvarVersoaAnes(versao);
    }
}
