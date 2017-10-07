package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.AnestesicoDao;
import com.des.odontec.equipe.odontec.Model.Anestesico;

import java.util.ArrayList;

/**
 * Created by Antonio on 21/09/2017.
 */

public class AnestesicoController {

    AnestesicoDao anestesicoDao;
    public AnestesicoController(Context context) {
        anestesicoDao = new AnestesicoDao(context);
    }



    public String[] listarAnestesicos() {
        int i=0;
        ArrayList<Anestesico> listaAnestesicos= anestesicoDao.listarAnestesicos();
        String[] anestesicos = new String[listaAnestesicos.size()];

        for (Anestesico an : listaAnestesicos) {
            anestesicos[i] = an.getTipoAnestesico();
            i++;
        }
        return anestesicos;
    }

    public void pegarDadosBD(){
        anestesicoDao.pegarDadosBD();
    }
}
