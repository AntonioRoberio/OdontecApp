package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 01/11/2017.
 */

public class Quiz {
    private String pergunta;
    private String respostaA;
    private String respostaB;
    private String respostaC;
    private String respostaD;
    private String respostaE;
    private String altCorreta;
    private String id;

    public Quiz() {

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> dadosSalvar = new HashMap<>();
        dadosSalvar.put("pergunta", getPergunta());
        dadosSalvar.put("respostaA", getRespostaA());
        dadosSalvar.put("respostaB", getRespostaB());
        dadosSalvar.put("respostaC", getRespostaC());
        dadosSalvar.put("respostaD", getRespostaD());
        dadosSalvar.put("respostaE", getRespostaE());
        dadosSalvar.put("altCorreta", getAltCorreta());
        return dadosSalvar;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getRespostaA() {
        return respostaA;
    }

    public void setRespostaA(String respostaA) {
        this.respostaA = respostaA;
    }

    public String getRespostaB() {
        return respostaB;
    }

    public void setRespostaB(String respostaB) {
        this.respostaB = respostaB;
    }

    public String getRespostaC() {
        return respostaC;
    }

    public void setRespostaC(String respostaC) {
        this.respostaC = respostaC;
    }

    public String getRespostaD() {
        return respostaD;
    }

    public void setRespostaD(String respostaD) {
        this.respostaD = respostaD;
    }

    public String getRespostaE() {
        return respostaE;
    }

    public void setRespostaE(String respostaE) {
        this.respostaE = respostaE;
    }

    public String getAltCorreta() {
        return altCorreta;
    }

    public void setAltCorreta(String altCorreta) {
        this.altCorreta = altCorreta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
