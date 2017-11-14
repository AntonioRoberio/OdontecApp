package com.des.odontec.equipe.odontec.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.Controller.PlacarQuizController;
import com.des.odontec.equipe.odontec.Controller.UsuarioController;
import com.des.odontec.equipe.odontec.Dao.QuizDao;
import com.des.odontec.equipe.odontec.Model.PlacarQuiz;
import com.des.odontec.equipe.odontec.Model.Quiz;
import com.des.odontec.equipe.odontec.Model.Usuario;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private TextView qntPer;
    private Button ajuda;
    private Button pular;
    private Button sair;
    private Quiz quiz;

    private Preferencias preferencias;

    private Timer tempo;
    private int conteTempo = 0;
    private String opc = "";

    private QuizDao quizDao;
    private ArrayList<Quiz> quizzes;
    private PlacarQuizController placarQuizController;
    private UsuarioController usuarioController;

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
        qntPer = (TextView) findViewById(R.id.qntPerguntas);
        pular = (Button) findViewById(R.id.pular);
        ajuda = (Button) findViewById(R.id.ajuda);
        sair = (Button) findViewById(R.id.sair);
        quizDao = new QuizDao(Quizz.this);
        quizzes = quizDao.listarPerguntas();
        placarQuizController=new PlacarQuizController(this);
        usuarioController=new UsuarioController(this);

        QuizDao quizDao = new QuizDao(Quizz.this);
        quizDao.pegarDadosBD();
        preferencias = new Preferencias(this);
        perguntas(preferencias.retornaQuiz());
        pontuacao.setText(preferencias.retornaPontosQuiz("pontos") + "");
        acertos.setText(preferencias.retornaPontosQuiz("acertos") + "");
        erros.setText(preferencias.retornaPontosQuiz("erros") + "");
        preferencias.quantidadeDeperguntas(quizDao.listarPerguntas().size(),"tamanho");
        qntPer.setText((preferencias.retornoquantidadeDeperguntas("atual")+1)+"/"+preferencias.retornoquantidadeDeperguntas("tamanho")+"");

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

        if (!preferencias.retornaStatusBotoes("proxima")) {
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

        if (!preferencias.retornaStatusBotoes("altCorreta")) {
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
        alterA.setText("A) " + quizz.getRespostaA());
        alterB.setText("B) " + quizz.getRespostaB());
        alterC.setText("C) " + quizz.getRespostaC());
        alterD.setText("D) " + quizz.getRespostaD());
        alterE.setText("E) " + quizz.getRespostaE());

        alterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaA(), quizz.getAltCorreta(), v);
            }
        });
        alterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaB(), quizz.getAltCorreta(), v);
            }
        });
        alterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaC(), quizz.getAltCorreta(), v);
            }
        });
        alterD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaD(), quizz.getAltCorreta(), v);
            }
        });
        alterE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarResposta(quizz.getRespostaE(), quizz.getAltCorreta(), v);
            }
        });

        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Quizz.this, "A resposta correta é " + quizz.getAltCorreta(), Toast.LENGTH_LONG).show();
                preferencias.statusBotoes(false, "altCorreta");
            }
        });

    }


    public void confirmarResposta(final String resposta, final String altCorreta, final View v) {
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
                opc = "conf";
                contTempo(resposta, altCorreta, v);
            }
        });
        alertaConfirmacao.create();
        alertaConfirmacao.show();
    }

    public void contTempo(final String resposta, final String correta, final View v) {
        tempo = new Timer();
        tempo.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (opc.equals("conf")) {
                            if (conteTempo == 3) {
                                v.setBackgroundResource(R.drawable.botaoquiz);
                                confResposta(resposta, correta, v);
                                tempo.cancel();
                                conteTempo = 0;
                            } else if (conteTempo % 2 == 0) {
                                v.setBackgroundResource(R.drawable.botaoquizconf);
                            } else if (conteTempo % 2 == 1) {
                                v.setBackgroundResource(R.drawable.botaoquiz);
                            }
                        } else {
                            if (conteTempo == 1) {
                                preferencias.quantidadeDeperguntas(preferencias.retornoquantidadeDeperguntas("atual")+1,"atual");
                                Intent intent = new Intent(Quizz.this, Quizz.class);
                                startActivity(intent);
                                finish();
                                tempo.cancel();
                            }
                        }
                        conteTempo++;
                    }
                });
            }
        }, 500, 1000);
    }

    public void confResposta(final String resposta, String correta, final View v) {
        QuizDao quizDao = new QuizDao(Quizz.this);
        ArrayList<Quiz> quizzes = quizDao.listarPerguntas();
        if (resposta.equalsIgnoreCase(correta)) {
            v.setBackgroundResource(R.drawable.botaoquizcorreta);
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("pontos") + 100, "pontos");
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("acertos") + 1, "acertos");
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("status") + 25, "status");
        } else {
            v.setBackgroundResource(R.drawable.botaoquizerrada);
            preferencias.pontosQuiz(preferencias.retornaPontosQuiz("erros") + 1, "erros");
        }
        final Preferencias preferencias = new Preferencias(Quizz.this);
        if (quizzes.size() - 1 > (preferencias.retornaQuiz())) {
            preferencias.quiz(preferencias.retornaQuiz() + 1);
            opc = "nojaPergunta";
            contTempo("", "", v);
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
                    PlacarQuiz placarQuiz=new PlacarQuiz();
                    placarQuiz.setNome(usuarioController.exibirDados().getNome().toString());
                    placarQuiz.setPontos(preferencias.retornaPontosQuiz("pontos")+"");
                    placarQuiz.setAcertos(preferencias.retornaPontosQuiz("acertos")+"");
                    placarQuiz.setErros(preferencias.retornaPontosQuiz("erros") + "");
                    placarQuizController.salvarPlacar(placarQuiz);
                    finish();
                    preferencias.quiz(0);
                    preferencias.pontosQuiz(0, "pontos");
                    preferencias.pontosQuiz(0, "acertos");
                    preferencias.pontosQuiz(0, "erros");
                    preferencias.pontosQuiz(0, "status");
                    preferencias.quantidadeDeperguntas(0,"tamanho");
                    preferencias.quantidadeDeperguntas(0,"atual");
                    preferencias.statusBotoes(true, "proxima");
                    preferencias.statusBotoes(true, "altCorreta");
                }
            });
            alertaConfirmacao.create();
            alertaConfirmacao.show();

        }


    }
}
