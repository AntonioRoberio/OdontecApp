package com.des.odontec.equipe.odontec.ArquivosDePreferencia;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Antonio on 24/09/2017.
 */

public class Preferencias {
    private Context context;

    public Preferencias(Context context) {
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

    public void salvarVersaoQuiz(String valor, String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("gerarAnestesico", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (tipo.equals("verQuiz")) {
            editor.putString("versaoQuiz", valor);
        } else {
            editor.putString("quantidadeQuiz", valor);
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
        } else if (valor.equals("quiz")) {
            return preferences.getString("versaoQuiz", "sem valor");
        } else if (valor.equals("patologia")) {
            return preferences.getString("versaoPatol", "sem valor");
        } else if (valor.equals("contAlt")) {
            return preferences.getString("quantidadeAlt", "0");
        } else if (valor.equals("contAnes")) {
            return preferences.getString("quantidadeAnes", "0");
        } else if (valor.equals("contPatol")) {
            return preferences.getString("quantidadePtl", "0");
        } else if (valor.equals("contQuiz")) {
            return preferences.getString("quantidadeQuiz", "0");
        } else {
            return "";
        }

    }

    public void alterSenha(String status) {
        SharedPreferences preferences = context.getSharedPreferences("statusSenhaAlterada", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("status", Boolean.parseBoolean(status));
        editor.commit();
    }

    public Boolean retornoAlterSenha() {
        SharedPreferences preferences = context.getSharedPreferences("statusSenhaAlterada", context.MODE_PRIVATE);
        return preferences.getBoolean("status", false);
    }

    public void login(String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("statusLogin", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", tipo);
        editor.commit();
    }

    public String retornaLogin() {
        SharedPreferences preferences = context.getSharedPreferences("statusLogin", context.MODE_PRIVATE);
        return preferences.getString("login", "");
    }

    public void quiz(int valor) {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("qst", valor);
        editor.commit();
    }

    public int retornaQuiz() {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        return preferences.getInt("qst", 0);
    }

    public void pontosQuiz(int valor, String opcao) {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (opcao.equals("pontos")) {
            editor.putInt("pontos", valor);
        } else if (opcao.equals("acertos")) {
            editor.putInt("acertos", valor);
        } else if (opcao.equals("erros")) {
            editor.putInt("erros", valor);
        } else if (opcao.equals("status")) {
            editor.putInt("status", valor);
        }

        editor.commit();
    }

    public int retornaPontosQuiz(String opcao) {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        if (opcao.equals("pontos")) {
            return preferences.getInt("pontos", 0);
        } else if (opcao.equals("acertos")) {
            return preferences.getInt("acertos", 0);
        } else if (opcao.equals("erros")) {
            return preferences.getInt("erros", 0);
        } else {
            return preferences.getInt("status", 2);
        }
    }

    public void statusBotoes(boolean valor, String opcao) {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (opcao.equals("proxima")) {
            editor.putBoolean("btPular", valor);
        } else {
            editor.putBoolean("btMetade", valor);
        }
        editor.commit();
    }

    public boolean retornaStatusBotoes(String opcao) {
        SharedPreferences preferences = context.getSharedPreferences("jogoQuiz", context.MODE_PRIVATE);
        if (opcao.equals("proxima")) {
            return preferences.getBoolean("btPular", true);
        } else {
            return preferences.getBoolean("btMetade", true);
        }

    }

    public void primeiroAcesso(int valor) {
        SharedPreferences preferences = context.getSharedPreferences("acesso", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("contAcesso", valor);
        editor.commit();
    }

    public int retornoPrimeiroAcesso() {
        SharedPreferences preferences = context.getSharedPreferences("acesso", context.MODE_PRIVATE);
        return preferences.getInt("contAcesso", 0);
    }

    public void quantidadeDeperguntas(int valor,String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("qntPerguntas", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(tipo.equals("tamanho")){
            editor.putInt("tamanho", valor);
        }else{
            editor.putInt("atual", valor);
        }

        editor.commit();
    }

    public int retornoquantidadeDeperguntas(String tipo) {
        SharedPreferences preferences = context.getSharedPreferences("qntPerguntas", context.MODE_PRIVATE);
        if(tipo.equals("tamanho")){
            return preferences.getInt("tamanho", 0);
        }else{
            return preferences.getInt("atual", 0);
        }
    }

}
