package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.des.odontec.equipe.odontec.Controller.AlteracaoController;
import com.des.odontec.equipe.odontec.Controller.AnestesicoController;
import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.R;

public class SplashScreen extends AppCompatActivity {

    private AlteracaoController alteracaoController;
    private AnestesicoController anestesicoController;

        private  int tempo = 5500;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
            QuizDao quizDao = new QuizDao(SplashScreen.this);
            quizDao.pegarDadosBD();
            alteracaoController = new AlteracaoController(SplashScreen.this);
            alteracaoController.pegarDadosBD();
            anestesicoController = new AnestesicoController(SplashScreen.this);
            anestesicoController.pegarDadosBD();

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


