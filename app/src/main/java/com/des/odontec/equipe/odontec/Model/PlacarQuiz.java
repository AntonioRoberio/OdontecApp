package com.des.odontec.equipe.odontec.Model;

/**
 * Created by Antonio on 12/11/2017.
 */

public class PlacarQuiz {
    private String pontos;
    private String acertos;
    private String erros;
    private String data;
    private int id;


    public PlacarQuiz() {
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getAcertos() {
        return acertos;
    }

    public void setAcertos(String acertos) {
        this.acertos = acertos;
    }

    public String getErros() {
        return erros;
    }

    public void setErros(String erros) {
        this.erros = erros;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
