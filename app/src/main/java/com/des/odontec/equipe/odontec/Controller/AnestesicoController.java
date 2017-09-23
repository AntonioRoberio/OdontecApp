package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.AnestesicoDao;
import com.des.odontec.equipe.odontec.Model.Anestesico;

import java.util.ArrayList;

/**
 * Created by Antonio on 21/09/2017.
 */

public class AnestesicoController {
    Context context;

    public AnestesicoController(Context context) {
        this.context = context;
    }

    AnestesicoDao anestesicoDao = new AnestesicoDao(context);

    public ArrayList<Anestesico> buscar() {
        return anestesicoDao.listarAnestesicos();
    }
}
