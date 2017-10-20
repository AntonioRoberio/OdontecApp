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
    public String[] listarPatologias(){
        String[] patologias;

        ArrayList<Patologia> ptlg=patologiaDao.listarPatologias();
       patologias=new String[ptlg.size()];

        for (Patologia ptl : ptlg) {
            patologias[i] = ptl.getTipoPatologia();
            i++;
        }

        return patologias;
    }
}
