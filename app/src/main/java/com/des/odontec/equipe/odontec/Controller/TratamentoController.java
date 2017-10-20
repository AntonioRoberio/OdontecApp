package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;
import com.des.odontec.equipe.odontec.Dao.TratamentoDao;
import com.des.odontec.equipe.odontec.Model.Tratamento;

import java.util.ArrayList;

/**
 * Created by Antonio on 20/10/2017.
 */

public class TratamentoController {

    private TratamentoDao tratamentoDao;
    public TratamentoController(Context context){
        tratamentoDao=new TratamentoDao(context);
    }

    public void pegarDados(){
        tratamentoDao.pegarDadosBD();
    }

    public String listarTratamento(String escolha){
        String tratamentos="";

        ArrayList<Tratamento> trat=tratamentoDao.listarTratamento();

        for (Tratamento tra : trat) {
            if(tra.getTipoTratamento().equals(escolha)){
                tratamentos = tra.getTipoTratamento();
                break;
            }

        }

        return tratamentos;
    }
}
