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

    public void salvarVersoaAnes(int versao) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(preferences.getInt("versaoAnes",0)==0){
            editor.putInt("versaoAnes",versao);
        }else {
            editor.putInt("versaoAnes",versao+preferences.getInt("versaoAnes",0));
        }
        editor.commit();
    }

    public void salvarVersoaAlter(int versao) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(preferences.getInt("versaoAlter",0)==0){
            editor.putInt("versaoAlter",versao);
        }else {
            editor.putInt("versaoAlter",versao+preferences.getInt("versaoAlter",0));
        }
        editor.commit();
    }

    public int retornoVersao(String valor) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        if (valor.equals("anestesico")){
            return preferences.getInt("versaoAnes", 0);
        }else{
            return preferences.getInt("versaoAlter", 0);
        }

    }
}
