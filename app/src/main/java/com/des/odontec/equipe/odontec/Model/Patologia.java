package com.des.odontec.equipe.odontec.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antonio on 18/10/2017.
 */

public class Patologia {
    private String selectPatologia;

    public String getSelectPatologia(){
        return selectPatologia;
    }

    public void setSelectPatologia(String selectPatologia){
        this.selectPatologia=selectPatologia;
    }

    @Exclude
    public Map<String,Object> patologia(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("patologia",getSelectPatologia());
        return hashMap;
    }
}

