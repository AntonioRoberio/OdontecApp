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

    public void salvarVersoaAnes(String valor, String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (tipo.equals("verAnes")) {
            editor.putString("versaoAnes", valor);
        } else {
            editor.putString("quantidadeAnes", valor);
        }
        editor.commit();
    }

    public void salvarVersoaAlter(String valor, String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (tipo.equals("verAlt")) {
            editor.putString("versaoAlter", valor);
        } else {
            editor.putString("quantidadeAlt", valor);
        }
        editor.commit();
    }

    public void salvarVersaoPatologia(String valor, String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (tipo.equals("verPat")) {
            editor.putString("versaoPatol", valor);
        } else {
            editor.putString("quantidadePtl", valor);
        }
        editor.commit();
    }
    public String retornoVersao(String valor) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        if (valor.equals("alteracao")) {
            return preferences.getString("versaoAlter", "sem valor");
        } else if (valor.equals("anestesico")) {
            return preferences.getString("versaoAnes", "sem valor");
        } else if (valor.equals("patologia")) {
            return preferences.getString("versaoAnes", "sem valor");
        } else if (valor.equals("contAlt")) {
            return preferences.getString("quantidadeAlt", "0");
        } else if (valor.equals("contAnes")) {
            return preferences.getString("quantidadeAnes", "0");
        }else if (valor.equals("contPatol")) {
            return preferences.getString("quantidadeAnes", "0");
        } else {
            return "";
        }

    }
}
