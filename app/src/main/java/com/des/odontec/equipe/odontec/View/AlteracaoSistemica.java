package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.R;

public class AlteracaoSistemica extends AppCompatActivity {
    private Spinner escolhaAlt;
    private Bundle bundle;
    private Button btAlt;
    private String valorTipPaci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteraca_sistemica);
        escolhaAlt = (Spinner) findViewById(R.id.selecioneAltera);
        btAlt = (Button) findViewById(R.id.btnAlteracao);

        final String[] alteracao = {"Norma Sistêmica", "anemia", "diabete", "asma","hipertensão", "hiperteriodismo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alteracao);
        escolhaAlt.setAdapter(adapter);

        Intent pegarInt = getIntent();
        Bundle tipoPaci = pegarInt.getExtras();
        valorTipPaci = tipoPaci.getString("tipo");

        bundle = new Bundle();
        bundle.putString("tipo", valorTipPaci);

        escolhaAlt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle.putString("alt", alteracao[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteracaoSistemica.this, TipoAnestesico.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
