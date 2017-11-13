package com.des.odontec.equipe.odontec.Controller;

import android.content.Context;

import com.des.odontec.equipe.odontec.Dao.PlacarQuizDao;
import com.des.odontec.equipe.odontec.Model.PlacarQuiz;

import java.util.ArrayList;

/**
 * Created by Antonio on 12/11/2017.
 */

public class PlacarQuizController {
    private PlacarQuizDao placarQuizDao;
    public PlacarQuizController(Context context){
     placarQuizDao=new PlacarQuizDao(context);
    }

    public void salvarPlacar(PlacarQuiz placarQuiz){
        placarQuizDao.salvarPaciente(placarQuiz);
    }

    public ArrayList<PlacarQuiz> exibirPlacar(){
        return placarQuizDao.exibirDadosPlacar();
    }
}
