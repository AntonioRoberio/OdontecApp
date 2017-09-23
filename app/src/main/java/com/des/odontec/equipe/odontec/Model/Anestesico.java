package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 18/09/2017.
 */

public class Anestesico {
    private String tipoAnestesico;

    public String getTipoAnestesico() {
        return tipoAnestesico;
    }

    public void setTipoAnestesico(String tipoAnestesico) {
        this.tipoAnestesico = tipoAnestesico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Anestesico() {

    }


    @Exclude
    public Map<String, Object> dadosSalvar() {
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("tipoAnestesico", getTipoAnestesico());
        return dados;
    }

}
