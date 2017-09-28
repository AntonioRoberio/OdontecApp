package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.R;

public class PesoPaciente extends AppCompatActivity {
    private EditText peso;
    private TextView nomeAnestesico;
    private Button envairPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_paciente);
        peso=(EditText) findViewById(R.id.pesoPaciente);
        nomeAnestesico=(TextView) findViewById(R.id.anestesicoEscolhido);
        envairPeso=(Button) findViewById(R.id.btnPeso);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        nomeAnestesico.setText(bundle.getString("tipoAnestesico"));

    }
}
