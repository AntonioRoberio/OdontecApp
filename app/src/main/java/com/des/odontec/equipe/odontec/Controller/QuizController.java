package com.des.odontec.equipe.odontec.Controller;

import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.View.Quizz;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Antonio on 14/11/2017.
 */

public class QuizController {
    private QuizDao quizDao;

    public QuizController(Context context) {
        this.quizDao = new QuizDao(context);
    }

    public void pegarPeguntas(){
        quizDao.pegarDadosBD();
    }

    public ArrayList<Quiz> totalPerguntas(){
        return quizDao.listarPerguntas();
    }
}
