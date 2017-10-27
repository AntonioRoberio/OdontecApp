package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

public class TipoPaciente extends AppCompatActivity {
    private Button btTipoPaciente;
    private ListView escolhaTipo;
    private Bundle bundle = new Bundle();
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_paciente);
        btTipoPaciente = (Button) findViewById(R.id.btTipoPaciente);
        escolhaTipo = (ListView) findViewById(R.id.escolhaTipo);

        final String[] tipoPessoas = {"Criança", "Adulto", "Idoso"};
        final View[] v = {new View(this)};

        escolhaTipo.setAdapter(new LayoutsAdpater(TipoPaciente.this, tipoPessoas));
        escolhaTipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (v[0] != null) {
                    v[0].setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.parseColor("#d3eef5"));
                bundle.putString("tipo", tipoPessoas[position]);
                v[0] = view;
            }
        });


        btTipoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle.containsKey("tipo")) {
                    Intent intent = new Intent(TipoPaciente.this, AlteracaoSistemica.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                } else {
                    Toast.makeText(TipoPaciente.this, "Selecione uma opção", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){

                setResult(1,data);
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
