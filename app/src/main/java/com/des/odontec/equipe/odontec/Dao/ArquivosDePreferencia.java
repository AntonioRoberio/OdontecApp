package com.des.odontec.equipe.odontec.Dao;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by Antonio on 24/09/2017.
 */

public class ArquivosDePreferencia {
    private Context context;

    public ArquivosDePreferencia(Context context) {
        this.context = context;
    }


    public void conteUsuario(int valor) {
        SharedPreferences preferences = context.getSharedPreferences("totalUsuarios", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getInt("contador", 0) == 0) {
            editor.putInt("contador", valor);
        } else {
            editor.putInt("contador", valor + preferences.getInt("contador", 0));
        }

        editor.commit();
    }

    public int conteUsuarioRetono() {
        SharedPreferences preferences = context.getSharedPreferences("totalUsuarios", context.MODE_PRIVATE);
        return preferences.getInt("contador", 0);
    }


    public void salvarVersoaAnes(int versao) {
        SharedPreferences preferences = context.getSharedPreferences("versaoAnes", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(preferences.getInt("versao",0)==0){
            editor.putInt("versao",versao);
        }else {
            editor.putInt("versao",versao+preferences.getInt("versao",0));
        }
        editor.commit();
    }

    public int versaoAnes() {
        SharedPreferences preferences = context.getSharedPreferences("versaoAnes", context.MODE_PRIVATE);
        return preferences.getInt("versao", 0);
    }
}
