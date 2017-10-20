package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 18/10/2017.
 */

public class Patologia {
    private String tipoPatologia;
    private String id;

    public String getTipoPatologia() {
        return tipoPatologia;
    }

    public void setTipoPatologia(String tipoPatologia) {
        this.tipoPatologia = tipoPatologia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public Map<String,Object> patologia(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("patologia",getTipoPatologia());
        return hashMap;
    }
}

