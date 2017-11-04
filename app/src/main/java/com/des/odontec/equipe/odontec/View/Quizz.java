package com.des.odontec.equipe.odontec.View;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class Quizz extends AppCompatActivity {
    private TextView pontuacao;
    private TextView acertos;
    private TextView erros;
    private TextView pergunta;
    private TextView alterA;
    private TextView alterB;
    private TextView alterC;
    private TextView alterD;
    private TextView alterE;
    private Button meioAMeio;
    private Button pular;
    private Button sair;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        pontuacao = (TextView) findViewById(R.id.pontos);
        acertos = (TextView) findViewById(R.id.acertos);
        erros = (TextView) findViewById(R.id.erros);
        pergunta = (TextView) findViewById(R.id.pgt);
        alterA = (TextView) findViewById(R.id.alterA);
        alterB = (TextView) findViewById(R.id.alterB);
        alterC = (TextView) findViewById(R.id.alterC);
        alterD = (TextView) findViewById(R.id.alterD);
        alterE = (TextView) findViewById(R.id.alterE);
        pular = (Button) findViewById(R.id.pular);
        meioAMeio = (Button) findViewById(R.id.metade);
        sair = (Button) findViewById(R.id.sair);
        progressBar = (ProgressBar) findViewById(R.id.status);

        progressBar.setBackgroundColor(Color.WHITE);
        QuizDao quizDao = new QuizDao(Quizz.this);
        //quizDao.pegarDadosBD2();
        ArrayList<Quiz> quizzes = quizDao.listarPerguntas();

        for (final Quiz q : quizzes) {
            pergunta.setText(q.getPergunta());
            alterA.setText(q.getRespostaA());
            alterB.setText(q.getRespostaB());
            alterC.setText(q.getRespostaC());
            alterD.setText(q.getRespostaD());
            alterE.setText(q.getRespostaE());

            alterA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t(v.toString());
                }
            });
            alterB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t(v.toString());
                }
            });
            alterC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t(v.toString());
                }
            });
            alterD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t(v.toString());
                }
            });
            alterE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t(v.toString());
                }
            });
        }


    }

    public void t(String resultado) {

            Toast.makeText(Quizz.this, resultado, Toast.LENGTH_SHORT).show();

    }
}
