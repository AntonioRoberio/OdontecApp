package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

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

    private ArrayList<String> listaAnestesico(){
        String[] anestesicos={"Lidocaína 2% (com adrenalina)","Lidocaína 2% (Sem vasoconstritor)","Prilocaína 3% (com felipressina)",
                "Mepivacaína 2% (com vasoconstritor)","Mepivacaína 3% (sem vasoconstritor)","Mepivacaína 2% (com adrelanina)",
                "Bupivacaína 0.5%","Articaína 4% (com adrenalina)"};

        ArrayList<String> listaAnes=new ArrayList<>();
        if(tipoPa.equals("Criança") && tipoAlt.equals("Norma Sistêmica")){
            listaAnes.add(anestesicos[0]);
            listaAnes.add(anestesicos[2]);
            listaAnes.add(anestesicos[5]);
        }

        return listaAnes;
    }
}
