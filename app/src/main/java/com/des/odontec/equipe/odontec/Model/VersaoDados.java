package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 24/09/2017.
 */

public class VersaoDados {
    private String anestesico;
    private String alteracao;
    private String patologia;
    private String tratamento;


    public VersaoDados() {
    }

    @Exclude
    public Map<String,Object> dados(){
        HashMap<String,Object> d=new HashMap<>();
        d.put("anestesico",getAnestesico());
        d.put("alteracao",getAlteracao());
        d.put("patologia",getPatologia());
        d.put("tratamento",getTratamento());
        return d;
    }

    public String getAnestesico() {
        return anestesico;
    }

    public void setAnestesico(String anestesico) {
        this.anestesico = anestesico;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getTratamento(){
        return tratamento;
    }

    public void setTratamento(String tratamento){
        this.tratamento=tratamento;

    }
}
