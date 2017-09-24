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



    public ArrayList<Anestesico> listarAnestesicos() {
        return anestesicoDao.listarAnestesicos();

    }

    public void pegarDadosBD(){
        anestesicoDao.pegarDadosBD();
    }
}
