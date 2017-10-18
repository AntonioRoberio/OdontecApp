package com.des.odontec.equipe.odontec.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.des.odontec.equipe.odontec.Controller.AnestesicoController;
import com.des.odontec.equipe.odontec.Model.Anestesico;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;


public class TipoAnestesico extends AppCompatActivity {
    private Spinner list;
    private String tipoPa;
    private String tipoAlt;
    private Button enviar;
    private int i=0;
    private ArrayList<String> lista;
    private Bundle bundle;
    private Bundle valores;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_anestesico);
        list = (Spinner) findViewById(R.id.listaResultado);
        enviar=(Button) findViewById(R.id.btnEnviarAnestesico);

        intent = getIntent();
        if(intent != null){
            valores = intent.getExtras();
            if(valores!=null){
                tipoPa = valores.getString("tipo");
                tipoAlt = valores.getString("alt");
            }else{
                tipoPa = "sem valor";
                tipoAlt = "sem valor";
            }
        }

        AnestesicoController anestesicoController=new AnestesicoController(this);
        lista = anestesicoController.listaAnestesico(tipoPa,tipoAlt);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);


        list.setAdapter(adapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle=new Bundle();
                bundle.putString("tipoAnestesico",lista.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TipoAnestesico.this,PesoPaciente.class);
                bundle.putString("alt",tipoAlt);
                bundle.putString("tipo",tipoPa);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }

}
