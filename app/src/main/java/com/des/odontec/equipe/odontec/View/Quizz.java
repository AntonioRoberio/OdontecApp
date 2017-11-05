package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
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
        Preferencias preferencias=new Preferencias(this);
        //perguntas(preferencias.retornaQuiz());
        alterA.setText("hahahuahauahuahauhauahauhaua auhauahauhauahuaha hauahauhauahuahauhauahauhauahau aahauhauahauhauhauahau ajhaahuahauahuahauahuahauhaua");
    }

    public void perguntas(final int valor) {
        QuizDao quizDao = new QuizDao(Quizz.this);
        ArrayList<Quiz> quizzes = quizDao.listarPerguntas();
        Quiz quiz = quizzes.get(valor);



        final Quiz quizz = new Quiz();
        double i =  Math.random() * 5;
        int j=(int) i;
        switch (j) {
            case 0:
                quizz.setRespostaA(quiz.getRespostaE());
                quizz.setRespostaB(quiz.getRespostaD());
                quizz.setRespostaC(quiz.getRespostaC());
                quizz.setRespostaD(quiz.getRespostaB());
                quizz.setRespostaE(quiz.getRespostaA());
                break;
            case 1:
                quizz.setRespostaA(quiz.getRespostaA());
                quizz.setRespostaB(quiz.getRespostaC());
                quizz.setRespostaC(quiz.getRespostaE());
                quizz.setRespostaD(quiz.getRespostaD());
                quizz.setRespostaE(quiz.getRespostaB());
                break;
            case 2:
                quizz.setRespostaA(quiz.getRespostaA());
                quizz.setRespostaB(quiz.getRespostaB());
                quizz.setRespostaC(quiz.getRespostaE());
                quizz.setRespostaD(quiz.getRespostaC());
                quizz.setRespostaE(quiz.getRespostaD());
                break;
            case 3:
                quizz.setRespostaA(quiz.getRespostaB());
                quizz.setRespostaB(quiz.getRespostaA());
                quizz.setRespostaC(quiz.getRespostaE());
                quizz.setRespostaD(quiz.getRespostaC());
                quizz.setRespostaE(quiz.getRespostaD());
                break;
            case 4:
                quizz.setRespostaA(quiz.getRespostaD());
                quizz.setRespostaB(quiz.getRespostaB());
                quizz.setRespostaC(quiz.getRespostaA());
                quizz.setRespostaD(quiz.getRespostaC());
                quizz.setRespostaE(quiz.getRespostaE());
                break;
            default:
                quizz.setRespostaA(quiz.getRespostaA());
                quizz.setRespostaB(quiz.getRespostaB());
                quizz.setRespostaC(quiz.getRespostaC());
                quizz.setRespostaD(quiz.getRespostaD());
                quizz.setRespostaE(quiz.getRespostaE());

        }


        quizz.setPergunta(quiz.getPergunta());
        quizz.setAltCorreta(quiz.getAltCorreta());

        pergunta.setText(quizz.getPergunta());
        alterA.setText(quizz.getRespostaA());
        alterB.setText(quizz.getRespostaB());
        alterC.setText(quizz.getRespostaC());
        alterD.setText(quizz.getRespostaD());
        alterE.setText(quizz.getRespostaE());

        alterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confResposta(quizz.getRespostaA(), quizz.getAltCorreta());
            }
        });
        alterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confResposta(quizz.getRespostaB(), quizz.getAltCorreta());
            }
        });
        alterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confResposta(quizz.getRespostaC(), quizz.getAltCorreta());
            }
        });
        alterD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confResposta(quizz.getRespostaD(), quizz.getAltCorreta());
            }
        });
        alterE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confResposta(quizz.getRespostaE(), quizz.getAltCorreta());
            }
        });


    }


    public void confResposta(String resposta, String correta) {
        QuizDao quizDao = new QuizDao(Quizz.this);
        ArrayList<Quiz> quizzes = quizDao.listarPerguntas();
        if (resposta.equalsIgnoreCase(correta)) {
            Toast.makeText(Quizz.this, "Correta", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Quizz.this,"Errado", Toast.LENGTH_SHORT).show();
        }
        Preferencias preferencias=new Preferencias(Quizz.this);
        if (quizzes.size()-1 > (preferencias.retornaQuiz())) {
            Intent intent=new Intent(Quizz.this,Quizz.class);
            startActivity(intent);
            finish();
            preferencias.quiz(preferencias.retornaQuiz()+1);

        }else{
            Intent intent=new Intent(Quizz.this,InicialActivity.class);
            startActivity(intent);
            setResult(10);
            finish();
            preferencias.quiz(0);
        }


    }
}
