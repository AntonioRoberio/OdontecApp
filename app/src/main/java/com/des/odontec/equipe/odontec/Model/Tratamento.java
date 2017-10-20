package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 20/10/2017.
 */

public class Tratamento {

    private String tipoTratamento;
    private String id;

    public String getTipoTratamento() {
        return tipoTratamento;
    }

    public void setTipoTratamento(String tipoTratamento) {
        this.tipoTratamento = tipoTratamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public Map<String,Object> tratamento(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("tipoPatologia",getTipoTratamento());
        return hashMap;
    }
}
