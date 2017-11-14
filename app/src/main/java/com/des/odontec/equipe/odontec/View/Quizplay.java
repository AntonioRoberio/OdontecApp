package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.des.odontec.equipe.odontec.R;

public class Quizplay extends AppCompatActivity {

    private Button inicioquiz;
    private Button placar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizplay);

        inicioquiz = (Button) findViewById(R.id.inicioquiz);
        placar =(Button) findViewById(R.id.placar);

        inicioquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quizplay.this, Quizz.class);
                startActivityForResult(intent, 10);
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
}
