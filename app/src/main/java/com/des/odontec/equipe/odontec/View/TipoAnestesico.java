package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

public class TipoAnestesico extends AppCompatActivity {
    private Button btnEnviar;
    private Bundle bundle;
    private String tipoPa;
    private String tipoAlt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_anestesico);
        btnEnviar=(Button) findViewById(R.id.btnEnviar);

        Intent intent=getIntent();
        Bundle teste=intent.getExtras();
        tipoPa=teste.getString("tipo");
        tipoAlt=teste.getString("alt");

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TipoAnestesico.this,tipoPa+" "+tipoAlt,Toast.LENGTH_LONG).show();
            }
        });
    }
}
