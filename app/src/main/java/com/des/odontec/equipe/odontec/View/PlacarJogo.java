package com.des.odontec.equipe.odontec.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.des.odontec.equipe.odontec.Controller.PlacarQuizController;
import com.des.odontec.equipe.odontec.Model.PlacarQuiz;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;


public class PlacarJogo extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar_quiz);
        listView=(ListView) findViewById(R.id.listPlacar);
        PlacarQuizController placarQuizController=new PlacarQuizController(this);
        ArrayList<PlacarQuiz> placarQuizs=placarQuizController.exibirPlacar();
        listView.setAdapter(new AdapterPlacar(this,placarQuizs));

    }

}
