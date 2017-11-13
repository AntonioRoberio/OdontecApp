package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.PlacarQuizDao;

/**
 * Created by Antonio on 12/11/2017.
 */

public class PlacarQuizController {
    private PlacarQuizDao placarQuizDao;
    public PlacarQuizController(Context context){
     placarQuizDao=new PlacarQuizDao(context);
    }
}
