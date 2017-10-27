package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.AlteracaoController;
import com.des.odontec.equipe.odontec.R;

public class AlteracaoSistemica extends AppCompatActivity {
    private ListView escolhaAlt;
    private Bundle bundle;
    private Button btAlt;
    private String valorTipPaci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteraca_sistemica);
        escolhaAlt = (ListView) findViewById(R.id.selecioneAltera);
        btAlt = (Button) findViewById(R.id.btnAlteracao);

        final String[] alteracao = listaAlteracoes();
        escolhaAlt.setAdapter(new LayoutsAdpater(AlteracaoSistemica.this,alteracao));

        Intent pegarInt = getIntent();
        Bundle tipoPaci = pegarInt.getExtras();
        valorTipPaci = tipoPaci.getString("tipo");

        bundle = new Bundle();
        bundle.putString("tipo", valorTipPaci);
        final View[] v = {new View(this)};

        escolhaAlt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(v[0] != null){
                    v[0].setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.parseColor("#d3eef5"));
                bundle.putString("alt", alteracao[position]);
                v[0] =view;
            }
        });

        btAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle.containsKey("alt")){
                    Intent intent = new Intent(AlteracaoSistemica.this, TipoAnestesico.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                    //finish();
                }else{
                    Toast.makeText(AlteracaoSistemica.this, "Selecione uma opção", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public String[] listaAlteracoes() {
        String[] alter;
        AlteracaoController alteracaoController = new AlteracaoController(AlteracaoSistemica.this);
        return alter = alteracaoController.listarAlteracoes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode)
        {
            case 0:
                setResult(resultCode);
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
