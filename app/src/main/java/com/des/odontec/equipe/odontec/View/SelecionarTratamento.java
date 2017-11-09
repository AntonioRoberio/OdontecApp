package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.Controller.PatologiaController;
import com.des.odontec.equipe.odontec.R;

public class SelecionarTratamento extends AppCompatActivity {
    private TextView patologia;
    private TextView tratamento;
    private String id;
    private String escolha;
    private Button voltarPatologia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_tratamento);
        patologia = (TextView) findViewById(R.id.tipoPatologia);
        tratamento = (TextView) findViewById(R.id.tipoTratamento);
        voltarPatologia = (Button) findViewById(R.id.voltarPatologia);

        voltarPatologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecionarTratamento.this, SelecionarPatologia.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        patologia.setText(bundle.getString("ptlg"));
        id = bundle.get("id").toString();
        PatologiaController patologiaController = new PatologiaController(SelecionarTratamento.this);
        escolha = patologiaController.tratamento(id);
        tratamento.setText(escolha);

    }
}
