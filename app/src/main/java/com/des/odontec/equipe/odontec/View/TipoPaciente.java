package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.des.odontec.equipe.odontec.R;

public class TipoPaciente extends AppCompatActivity {
    private RadioButton tipoCrianca;
    private RadioButton tipoAdulto;
    private RadioButton tipoIdoso;
    private Button btTipoPaciente;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_paciente);
        tipoCrianca=(RadioButton) findViewById(R.id.tipoCrianca);
        tipoAdulto=(RadioButton) findViewById(R.id.tipoAdulto);
        tipoIdoso=(RadioButton) findViewById(R.id.tipoIdoso);
        btTipoPaciente=(Button) findViewById(R.id.btTipoPaciente);

        bundle=new Bundle();
        if(tipoCrianca.isChecked())
            bundle.putString("Tipo","crianca");
        if(tipoAdulto.isChecked())
            bundle.putString("Tipo","adulto");
        if(tipoIdoso.isChecked())
            bundle.putString("Tipo","idoso");

        btTipoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bundle.getString("Tipo").isEmpty()){
                    Intent intent=new Intent(TipoPaciente.this,AlteracaoSistemica.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
    }
}
