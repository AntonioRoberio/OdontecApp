

package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.des.odontec.equipe.odontec.ArquivosDePreferencia.Preferencias;
import com.des.odontec.equipe.odontec.R;

public class Quizplay extends AppCompatActivity {

    private Button inicioquiz;
    private Button placar;
    private Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizplay);

        inicioquiz = (Button) findViewById(R.id.inicioquiz);
        placar =(Button) findViewById(R.id.placar);

        preferencias = new Preferencias(this);
        preferencias.quiz(0);
        preferencias.pontosQuiz(0, "pontos");
        preferencias.pontosQuiz(0, "acertos");
        preferencias.pontosQuiz(0, "erros");
        preferencias.pontosQuiz(0, "status");
        preferencias.quantidadeDeperguntas(0,"tamanho");
        preferencias.quantidadeDeperguntas(0,"atual");
        preferencias.statusBotoes(true, "proxima");
        preferencias.statusBotoes(true, "altCorreta");

        inicioquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quizplay.this, Quizz.class);
                startActivityForResult(intent, 10);
                finish();
            }
        });
        placar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quizplay.this, PlacarJogo.class);
                startActivityForResult(intent, 10);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){
            setResult(10,data);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
