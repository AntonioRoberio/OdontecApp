package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.PatologiaDao;
import com.des.odontec.equipe.odontec.Model.Patologia;

import java.util.ArrayList;

/**
 * Created by Antonio on 20/10/2017.
 */

public class PatologiaController {
    private PatologiaDao patologiaDao;
    private int i=0;
    public PatologiaController(Context context){
        patologiaDao=new PatologiaDao(context);
    }

    public void pegarDados(){
        patologiaDao.pegarDadosBD();
    }

    public ArrayList<String[]> listarPatologias(){

        String patologias;
        String id;

        ArrayList<String[]> listaTratamento=new ArrayList<>();

        ArrayList<Patologia> ptlg=patologiaDao.listarPatologias();


        for (Patologia ptl : ptlg) {
            patologias=ptl.getTipoPatologia();
            id=ptl.getId();
            String[] valores=new String[2];
            valores[0]=patologias;
            valores[1]=id;
            listaTratamento.add(valores);

        }

        return listaTratamento;
    }

    public String tratamento(String valor){
        String tratamento="";

        ArrayList<Patologia> ptlg=patologiaDao.listarPatologias();

        for(Patologia ptl : ptlg){
            if(ptl.getId().equals(valor)){
                tratamento=ptl.getTipoTratamento();
            }
        }

        return tratamento;
    }

}
