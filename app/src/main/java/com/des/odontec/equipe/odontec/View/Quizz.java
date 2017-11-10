package com.des.odontec.equipe.odontec.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    private Button ajuda;
    private Button pular;
    private Button sair;
    private Quiz quiz;
    private ProgressBar progressBar;
    private Preferencias preferencias;
    private TextView statusProgresso;

    private QuizDao quizDao;
    private ArrayList<Quiz> quizzes;

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
        ajuda = (Button) findViewById(R.id.ajuda);
        sair = (Button) findViewById(R.id.sair);
        progressBar = (ProgressBar) findViewById(R.id.status);
        statusProgresso = (TextView) findViewById(R.id.statusProgresso);
        int cor=Color.parseColor("#DCDCDC");
        progressBar.setBackgroundColor(cor);
        quizDao = new QuizDao(Quizz.this);
        quizzes = quizDao.listarPerguntas();

        QuizDao quizDao = new QuizDao(Quizz.this);
        quizDao.pegarDadosBD();
        preferencias = new Preferencias(this);
        progressBar.setProgress(preferencias.retornaPontosQuiz("status"));
        statusProgresso.setText(preferencias.retornaPontosQuiz("status")+"%");
        perguntas(preferencias.retornaQuiz());
        pontuacao.setText(preferencias.retornaPontosQuiz("pontos") + "");
        acertos.setText(preferencias.retornaPontosQuiz("acertos") + "");
        erros.setText(preferencias.retornaPontosQuiz("erros") + "");

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmar = new AlertDialog.Builder(Quizz.this);
                confirmar.setTitle("Sair?").setMessage("Tem certeza que deseja sair do jogo?").setCancelable(true)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Quizz.this, "Cancelado", Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Quizz.this, InicialActivity.class);
                        startActivity(intent);
                       finish();
                       setResult(10);
                        preferencias.quiz(0);
                        preferencias.pontosQuiz(0, "pontos");
                        preferencias.pontosQuiz(0, "acertos");
                        preferencias.pontosQuiz(0, "erros");
                        preferencias.pontosQuiz(2, "status");
                        preferencias.statusBotoes(true, "proxima");
                        preferencias.statusBotoes(true, "altCorreta");

                    }
                });
                confirmar.create();
                confirmar.show();
            }
        });

        if(!preferencias.retornaStatusBotoes("proxima")){
            pular.setEnabled(false);
            pular.setBackground(getResources().getDrawable(R.drawable.botaodesabilitado));
        }
        pular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizzes.size() - 1 > (preferencias.retornaQuiz())) {
                    Intent intent = new Intent(Quizz.this, Quizz.class);
                    startActivity(intent);
                    preferencias.quiz(preferencias.retornaQuiz() + 1);
                    preferencias.statusBotoes(false, "proxima");
                    finish();

                }
            }
        });

        if(!preferencias.retornaStatusBotoes("altCorreta")){
            ajuda.setEnabled(false);
            ajuda.setBackground(getResources().getDrawable(R.drawable.botaodesabilitado));
        }
    }


    public void perguntas(final int valor) {

        quiz = quizzes.get(valor);
        final Quiz quizz = new Quiz();
        double i = Math.random() * 5;
        int j = (int) i;
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
        alterA.setText("A) "+quizz.getRespostaA());
        alterB.setText("B) "+quizz.getRespostaB());
        alterC.setText("C) "+quizz.getRespostaC());
        alterD.setText("D) "+quizz.getRespostaD());
        alterE.setText("E) "+quizz.getRespostaE());

        alterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaA(), quizz.getAltCorreta());
            }
        });
        alterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaB(), quizz.getAltCorreta());
            }
        });
        alterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaC(), quizz.getAltCorreta());
            }
        });
        alterD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaD(), quizz.getAltCorreta());
            }
        });
        alterE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaE(), quizz.getAltCorreta());
            }
        });

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Quizz.this,"A resposta correta é "+quizz.getAltCorreta(), Toast.LENGTH_LONG).show();
                preferencias.statusBotoes(false, "altCorreta");
            }
        });

    }


    public void confirmarResposta(final String resposta, final String altCorreta) {
        AlertDialog.Builder alertaConfirmacao = new AlertDialog.Builder(Quizz.this);
        alertaConfirmacao.setTitle("Confirmar resposta").setMessage("Resposta: " + resposta + "\n\n" + "É sua resposta final?")
                .setCancelable(false).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Quizz.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confResposta(resposta, altCorreta);
            }
        });
        alertaConfirmacao.create();
        alertaConfirmacao.show();
    }

    public void confResposta(final String resposta, String correta) {
        QuizDao quizDao = new QuizDao(Quizz.this);
        ArrayList<Quiz> quizzes = quizDao.listarPerguntas();
        if (resposta.equalsIgnoreCase(correta)) {
            Toast.makeText(Quizz.this, "Correta", Toast.LENGTH_SHORT).show();
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("pontos") + 100, "pontos");
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("acertos") + 1, "acertos");
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("status") + 25, "status");
        } else {
            Toast.makeText(Quizz.this, "Errado", Toast.LENGTH_SHORT).show();
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("erros") + 1, "erros");
        }
        final Preferencias preferencias = new Preferencias(Quizz.this);
        if (quizzes.size() - 1 > (preferencias.retornaQuiz())) {
            Intent intent = new Intent(Quizz.this, Quizz.class);
            startActivity(intent);
            finish();
            preferencias.quiz(preferencias.retornaQuiz() + 1);

        } else {
            AlertDialog.Builder alertaConfirmacao = new AlertDialog.Builder(Quizz.this);
            alertaConfirmacao.setTitle("Resultado Quiz Odontec").setMessage("Pontos: " + preferencias.retornaPontosQuiz("pontos") + "\n" +
                    "Acertos: " + preferencias.retornaPontosQuiz("acertos") + "\n" +
                    "Erros: " + preferencias.retornaPontosQuiz("erros") + "")
                    .setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Quizz.this, InicialActivity.class);
                    startActivity(intent);
                    setResult(10);
                    finish();
                    preferencias.quiz(0);
                    preferencias.pontosQuiz(0, "pontos");
                    preferencias.pontosQuiz(0, "acertos");
                    preferencias.pontosQuiz(0, "erros");
                    preferencias.pontosQuiz(2, "status");
                    preferencias.statusBotoes(true, "proxima");
                    preferencias.statusBotoes(true, "altCorreta");
                }
            });
            alertaConfirmacao.create();
            alertaConfirmacao.show();

        }


    }
}
