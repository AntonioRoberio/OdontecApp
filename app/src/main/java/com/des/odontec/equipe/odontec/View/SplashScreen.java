package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Controller.AlteracaoController;
import com.des.odontec.equipe.odontec.Controller.AnestesicoController;
import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.Controller.QuizController;
import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.R;

public class SplashScreen extends AppCompatActivity {

    private AlteracaoController alteracaoController;
    private AnestesicoController anestesicoController;
    private PatologiaController patologiaController;
    private QuizController quizDao;

    private int tempo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        quizDao = new QuizController(this);
        quizDao.pegarPeguntas();
        alteracaoController = new AlteracaoController(this);
        alteracaoController.pegarDadosBD();
        anestesicoController = new AnestesicoController(this);
        anestesicoController.pegarDadosBD();
        patologiaController=new PatologiaController(this);
        patologiaController.pegarDados();
        Preferencias preferencias = new Preferencias(this);
        if (preferencias.retornoPrimeiroAcesso() == 0) {
            tempo = 5500;
        } else {
            tempo = 2000;
        }

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(SplashScreen.this, MainActivity_Login.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, tempo);
    }
}


