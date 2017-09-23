package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

public class TipoPaciente extends AppCompatActivity {
    private Button btTipoPaciente;
    private Spinner escolhaTipo;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_paciente);
        btTipoPaciente = (Button) findViewById(R.id.btTipoPaciente);
        escolhaTipo = (Spinner) findViewById(R.id.escolhaTipo);

        final String[] tipoPessoas = {"Criança", "Adulto", "Idoso"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipoPessoas);
        escolhaTipo.setAdapter(adapter);

        escolhaTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bundle = new Bundle();
                bundle.putString("tipo", tipoPessoas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btTipoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipoPaciente.this, AlteracaoSistemica.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
