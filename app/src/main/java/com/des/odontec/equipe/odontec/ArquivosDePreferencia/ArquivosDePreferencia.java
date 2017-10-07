package com.des.odontec.equipe.odontec.ArquivosDePreferencia;

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

    public void salvarVersoaAnes(String versao) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("versaoAnes",versao);
        editor.commit();
    }

    public void salvarVersoaAlter(String versao) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
            editor.putString("versaoAlter",versao);
        editor.commit();
    }

    public String retornoVersao(String valor) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        if (valor.equals("alteracao")){
            return preferences.getString("versaoAlter","sem valor");
        }else if(valor.equals("anestesico")){
            return preferences.getString("versaoAnes","sem valor");
        }else{
            return "";
        }

    }
}
